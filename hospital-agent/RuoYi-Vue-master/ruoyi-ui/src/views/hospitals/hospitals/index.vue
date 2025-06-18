<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="医院名称" prop="hospitalName">
        <el-input
          v-model="queryParams.hospitalName"
          placeholder="请输入医院名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['hospitals:hospitals:add']"
        >新增</el-button>
      </el-col>
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="success"-->
<!--          plain-->
<!--          icon="el-icon-edit"-->
<!--          size="mini"-->
<!--          :disabled="single"-->
<!--          @click="handleUpdate"-->
<!--          v-hasPermi="['hospitals:hospitals:edit']"-->
<!--        >修改</el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="danger"-->
<!--          plain-->
<!--          icon="el-icon-delete"-->
<!--          size="mini"-->
<!--          :disabled="multiple"-->
<!--          @click="handleDelete"-->
<!--          v-hasPermi="['hospitals:hospitals:remove']"-->
<!--        >删除</el-button>-->
<!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['hospitals:hospitals:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="hospitalsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column type="expand" align="center" prop="description">
        <template v-slot="scope">
          <el-card>
            {{scope.row.description}}
          </el-card>
        </template>
      </el-table-column>
      <el-table-column label="医院名称" align="center" prop="hospitalName" />
      <el-table-column label="医院等级" align="center" prop="hospitalLevel">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.hospital_level" :value="scope.row.hospitalLevel"/>
        </template>
      </el-table-column>
      <el-table-column label="地址" align="center" prop="address" />
      <el-table-column label="联系电话" align="center" prop="phone" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['hospitals:hospitals:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['hospitals:hospitals:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改医院信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="医院名称" prop="hospitalName">
          <el-input v-model="form.hospitalName" placeholder="请输入医院名称" />
        </el-form-item>
        <el-form-item label="医院等级" prop="hospitalLevel" >
          <el-select v-model="form.hospitalLevel" placeholder="请选择医院等级" style="width: 200px">
            <el-option
              v-for="dict in dict.type.hospital_level"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="联系电话" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入联系电话" />
        </el-form-item>
        <el-form-item label="地址" prop="address">
          <el-input type="textarea" v-model="form.address" placeholder="请输入地址" />
        </el-form-item>
        <el-form-item label="医院简介">
          <editor v-model="form.description" :min-height="192"/>
        </el-form-item>
        <el-divider content-position="center">科室信息信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddDepartments">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteDepartments">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="departmentsList" :row-class-name="rowDepartmentsIndex" @selection-change="handleDepartmentsSelectionChange" ref="departments">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="科室名称" prop="deptName" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.deptName" placeholder="请输入科室名称" />
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listHospitals, getHospitals, delHospitals, addHospitals, updateHospitals } from "@/api/hospitals/hospitals";

export default {
  name: "Hospitals",
  dicts: ['hospital_level'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedDepartments: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 医院信息表格数据
      hospitalsList: [],
      // 科室信息表格数据
      departmentsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        hospitalName: null,
        deleted: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        hospitalName: [
          { required: true, message: "医院名称不能为空", trigger: "blur" }
        ],
        hospitalLevel: [
          { required: true, message: "医院等级不能为空", trigger: "change" }
        ],
        address: [
          { required: true, message: "地址不能为空", trigger: "blur" }
        ],
        phone: [
          { required: true, message: "联系电话不能为空", trigger: "blur" }
        ],
        description: [
          { required: true, message: "医院简介不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询医院信息列表 */
    getList() {
      this.loading = true;
      listHospitals(this.queryParams).then(response => {
        this.hospitalsList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        hospitalId: null,
        hospitalName: null,
        hospitalLevel: null,
        address: null,
        phone: null,
        description: null,
        createTime: null,
        updateTime: null,
        deleted: null
      };
      this.departmentsList = [];
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.hospitalId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加医院信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const hospitalId = row.hospitalId || this.ids
      getHospitals(hospitalId).then(response => {
        this.form = response.data;
        this.departmentsList = response.data.departmentsList;
        this.open = true;
        this.title = "修改医院信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.departmentsList = this.departmentsList;
          if (this.form.hospitalId != null) {
            updateHospitals(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addHospitals(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const hospitalIds = row.hospitalId || this.ids;
      this.$modal.confirm('是否确认删除医院信息编号为"' + hospitalIds + '"的数据项？').then(function() {
        return delHospitals(hospitalIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 科室信息序号 */
    rowDepartmentsIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 科室信息添加按钮操作 */
    handleAddDepartments() {
      // let obj = {};
      // obj.deptName = "";
      // obj.description = "";
      // obj.deleted = "";
      this.departmentsList.push({ deptName: "" });
    },
    /** 科室信息删除按钮操作 */
    handleDeleteDepartments() {
      if (this.checkedDepartments.length == 0) {
        this.$modal.msgError("请先选择要删除的科室信息数据");
      } else {
        const departmentsList = this.departmentsList;
        const checkedDepartments = this.checkedDepartments;
        this.departmentsList = departmentsList.filter(function(item) {
          return checkedDepartments.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleDepartmentsSelectionChange(selection) {
      this.checkedDepartments = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('hospitals/hospitals/export', {
        ...this.queryParams
      }, `hospitals_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
