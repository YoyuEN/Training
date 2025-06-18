<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="患者名称" prop="patientName">
        <el-input
          v-model="queryParams.patientName"
          placeholder="请输入患者名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="身份证号" prop="idCard">
        <el-input
          v-model="queryParams.idCard"
          placeholder="请输入身份证号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="医生名称" prop="doctorName">
        <el-input
          v-model="queryParams.doctorName"
          placeholder="请输入医生名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
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
          v-hasPermi="['appointments:appointments:add']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['appointments:appointments:edit']"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['appointments:appointments:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['appointments:appointments:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="appointmentsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="患者名称" align="center" prop="patientName"/>
      <el-table-column label="身份证号" align="center" prop="idCard"/>
      <el-table-column label="医生名称" align="center" prop="doctorName"/>
      <el-table-column label="医院名称" align="center" prop="hospitalName"/>
      <el-table-column label="科室名称" align="center" prop="deptName"/>
      <el-table-column label="就诊时段" align="center" prop="timeSlot"/>
      <el-table-column label="取消原因" align="center" prop="cancelReason"/>
      <el-table-column label="预约时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['appointments:appointments:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['appointments:appointments:remove']"
          >删除
          </el-button>
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

    <!-- 添加或修改预约管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="医院名称" prop="hospitalName">
          <el-select v-model="form.hospitalName" @change="getDepartmentsByHospitalName" placeholder="请选择所属医院"
                     style="width: 100%">
            <el-option
              v-for="item in hospitalsList"
              :key="item.hospitalId"
              :label="item.hospitalName"
              :value="item.hospitalName"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="科室名称" prop="deptName">
          <el-select v-model="form.deptName" @change="searchDeptNameDoctors" placeholder="请选择所属科室" style="width: 100%">
            <el-option
              v-for="item in departmentsList"
              :key="item.deptId"
              :label="item.deptName"
              :value="item.deptName"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="医生名称" prop="doctorName">
          <el-select v-model="form.doctorName"  placeholder="请选择医生名称" style="width: 100%">
            <el-option
              v-for="item in doctorsList"
              :key="item.doctorId"
              :label="item.name"
              :value="item.name"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="患者名称" prop="patientName">
          <el-input v-model="form.patientName" placeholder="请输入患者名称"/>
        </el-form-item>
        <el-form-item label="身份证号" prop="idCard">
          <el-input v-model="form.idCard" placeholder="请输入身份证号"/>
        </el-form-item>
        <el-form-item label="就诊时段" prop="visitTime">
          <el-date-picker
            v-model="form.visitTime"
            type="datetime"
            placeholder="选择日期时间"
            value-format="yyyy-MM-dd HH:mm:ss"
          />
        </el-form-item>
        <el-form-item label="取消原因" prop="cancelReason">
          <el-input v-model="form.cancelReason" placeholder="请输入取消原因"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listAppointments,
  getAppointments,
  delAppointments,
  addAppointments,
  updateAppointments
} from "@/api/appointments/appointments";
import {getListHospitals} from "@/api/hospitals/hospitals";
import {getDepartmentsByHospitalName} from "@/api/departments/departments";
import {searchDeptNameDoctors} from "@/api/doctors/doctors";

export default {
  name: "Appointments",
  data() {
    return {
      hospitalsList: [],
      departmentsList: [],
      // 医生列表
      doctorsList: [],
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
      // 预约管理表格数据
      appointmentsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        patientName: null,
        idCard: null,
        doctorName: null,
        hospitalName: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        patientName: [
          {required: true, message: "患者名称不能为空", trigger: "blur"}
        ],
        idCard: [
          {required: true, message: "身份证号不能为空", trigger: "blur"}
        ],
        doctorName: [
          {required: true, message: "医生名称不能为空", trigger: "blur"}
        ],
        hospitalName: [
          {required: true, message: "医院名称不能为空", trigger: "blur"}
        ],
        deptName: [
          {required: true, message: "科室名称不能为空", trigger: "blur"}
        ],
        timeSlot: [
          {required: true, message: "就诊时段不能为空", trigger: "blur"}
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getHospitalsList();
  },
  methods: {
    //根据科室ID查询医生列表
    searchDeptNameDoctors(deptId) {
      searchDeptNameDoctors(deptId).then(response => {
        this.doctorsList = response.data;
      })
    },
    getDepartmentsByHospitalName(hospitalId) {
      getDepartmentsByHospitalName(hospitalId).then(response => {
        this.departmentsList = response.data;
      })
    },
    getHospitalsList() {
      getListHospitals().then(response => {
        this.hospitalsList = response.data;
      })
    },

    /** 查询预约管理列表 */
    getList() {
      this.loading = true;
      listAppointments(this.queryParams).then(response => {
        this.appointmentsList = response.rows;
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
        appointmentId: null,
        patientName: null,
        idCard: null,
        doctorName: null,
        hospitalName: null,
        deptName: null,
        timeSlot: null,
        cancelReason: null,
        createTime: null,
        updateTime: null
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
      this.ids = selection.map(item => item.appointmentId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加预约管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const appointmentId = row.appointmentId || this.ids
      getAppointments(appointmentId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改预约管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.appointmentId != null) {
            updateAppointments(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAppointments(this.form).then(response => {
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
      const appointmentIds = row.appointmentId || this.ids;
      this.$modal.confirm('是否确认删除预约管理编号为"' + appointmentIds + '"的数据项？').then(function () {
        return delAppointments(appointmentIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('appointments/appointments/export', {
        ...this.queryParams
      }, `appointments_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
