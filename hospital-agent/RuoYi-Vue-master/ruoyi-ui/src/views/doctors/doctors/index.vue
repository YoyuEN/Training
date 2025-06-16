<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="医生名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入医生名称"
          clearable
          size="small"
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
          v-hasPermi="['doctors:doctors:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['doctors:doctors:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['doctors:doctors:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['doctors:doctors:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="doctorsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column type="expand" align="center" prop="introduction" >
        <template v-slot="scope">
          <el-card>
            <div v-html="scope.row.introduction"></div>
          </el-card>
        </template>
      </el-table-column>
      <el-table-column label="医生名称" align="center" prop="name" />
      <el-table-column label="医生职称" align="center" prop="title" />
      <el-table-column label="擅长领域" align="center" prop="specialty" />
      <el-table-column label="从业年限" align="center" prop="workYears" />
      <el-table-column label="挂号费" align="center" prop="consultationFee" />
      <el-table-column label="所属科室" align="center" prop="deptName" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="修改时间" align="center" prop="updateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['doctors:doctors:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['doctors:doctors:remove']"
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

    <!-- 添加或修改医生管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row :gutter="5">
          <el-col :span="12">
            <el-form-item label="医生名称" prop="name">
              <el-input v-model="form.name" placeholder="请输入医生名称" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="所属科室" prop="deptId">
              <el-select v-model="form.deptId" placeholder="请选择所属科室" style="width: 100%">
                <el-option
                  v-for="item in departmentsList"
                  :key="item.deptId"
                  :label="item.deptName"
                  :value="item.deptId"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="职称" prop="title">
              <el-input v-model="form.title" placeholder="请输入职称" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="擅长领域" prop="specialty">
              <el-input v-model="form.specialty" placeholder="请输入擅长领域" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="从业年限" prop="workYears">
              <el-input-number aria-readonly="true" v-model="form.workYears" placeholder="请输入从业年限" style="width: 100%"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="挂号费" prop="consultationFee">
              <el-input-number aria-readonly="true" v-model="form.consultationFee" placeholder="请输入挂号费" style="width: 100%"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="5">
          <el-col >
            <el-form-item label="医生简介" prop="introduction">
              <editor v-model="form.introduction" type="textarea" placeholder="请输入内容" />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listDoctors, getDoctors, delDoctors, addDoctors, updateDoctors } from "@/api/doctors/doctors";
import { getDepartmentsList} from "@/api/departments/departments";

export default {
  name: "Doctors",
  data() {
    return {
      departmentsList: [],
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 医生管理表格数据
      doctorsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        deptId: [
          { required: true, message: "所属科室ID不能为空", trigger: "change" }
        ],
        name: [
          { required: true, message: "医生名称不能为空", trigger: "change" }
        ],
        title: [
          { required: true, message: "职称不能为空", trigger: "blur" }
        ],
        specialty: [
          { required: true, message: "擅长领域不能为空", trigger: "blur" }
        ],
        workYears: [
          { required: true, message: "从业年限不能为空", trigger: "blur" }
        ],
        consultationFee: [
          { required: true, message: "挂号费不能为空", trigger: "blur" }
        ],
        introduction: [
          { required: true, message: "医生简介不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getDepartmentsList();
  },
  methods: {
    /* */
    getDepartmentsList()  {
      getDepartmentsList().then(response => {
        this.departmentsList = response.data;
      });
    },
    /** 查询医生管理列表 */
    getList() {
      this.loading = true;
      listDoctors(this.queryParams).then(response => {
        this.doctorsList = response.rows;
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
        doctorId: null,
        deptId: null,
        name: null,
        title: null,
        specialty: null,
        workYears: null,
        consultationFee: null,
        introduction: null,
        createTime: null,
        updateTime: null,
        deleted: null
      };
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
      this.ids = selection.map(item => item.doctorId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加医生管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const doctorId = row.doctorId || this.ids
      getDoctors(doctorId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改医生管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.doctorId != null) {
            updateDoctors(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDoctors(this.form).then(response => {
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
      const doctorIds = row.doctorId || this.ids;
      this.$modal.confirm('是否确认删除医生管理编号为"' + doctorIds + '"的数据项？').then(function() {
        return delDoctors(doctorIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('doctors/doctors/export', {
        ...this.queryParams
      }, `doctors_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
