<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="医生ID" prop="doctorId">
        <el-input
          v-model="queryParams.doctorId"
          placeholder="请输入医生ID"
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
          v-hasPermi="['schedules:schedules:add']"
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
          v-hasPermi="['schedules:schedules:edit']"
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
          v-hasPermi="['schedules:schedules:remove']"
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
          v-hasPermi="['schedules:schedules:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="schedulesList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="医生名称" align="center" prop="doctorId">
        <template slot-scope="scope">
          <el-card style="background-color: #ffffff">
            <el-tag type="success" style="color: black">{{ scope.row.doctorName }} -- {{ scope.row.deptName }}</el-tag>
          </el-card>
        </template>
      </el-table-column>

      <el-table-column label="排班日期" align="center" prop="date">
        <template v-slot="scope">
          <el-card style="background-color: #ffffff">
            <el-tag type="success" style="color: black">{{ scope.row.date }}</el-tag>
          </el-card>
        </template>
      </el-table-column>
      <el-table-column label="上午时间" align="center" prop="morningStart">
        <template slot-scope="scope">
          <el-card v-if="scope.row.morningStart" style="background-color: #ffffff">
            <el-tag type="success" style="color: black">开始时间：{{
                scope.row.morningStart
              }}--结束时间：{{ scope.row.morningEnd }}
            </el-tag>
            <el-tag type="success" style="color: black">信号源数量：{{ scope.row.morningQuota }}</el-tag>
          </el-card>
          <el-card v-else>无排版信息</el-card>

        </template>
      </el-table-column>
      <el-table-column label="下午时间" align="center" prop="morningStart">
        <template slot-scope="scope">
          <el-card v-if="scope.row.afternoonStart" style="background-color: #ffffff">
            <el-tag type="success" style="color: black">开始时间：{{
                scope.row.afternoonStart
              }}--结束时间：{{ scope.row.afternoonEnd }}
            </el-tag>
            <el-tag type="success" style="color: black">信号源数量：{{ scope.row.afternoonQuota }}</el-tag>
          </el-card>
          <el-card v-else>无排版信息</el-card>
        </template>
      </el-table-column>
      <el-table-column label="夜间时间" align="center" prop="morningStart">
        <template slot-scope="scope">
          <el-card v-if="scope.row.nightStart" style="background-color: #ffffff">
            <el-tag type="success" style="color: black; background-color: #ffffff">开始时间：{{
                scope.row.nightStart
              }}--结束时间：{{ scope.row.nightEnd }}
            </el-tag>
            <el-tag type="success" style="color: black">信号源数量：{{ scope.row.nightQuota }}</el-tag>
          </el-card>
          <el-card v-else>无排版信息</el-card>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['schedules:schedules:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['schedules:schedules:remove']"
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

    <!-- 添加或修改排班管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1000px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="110px">
        <el-row >
          <el-col :span="12">
            <el-form-item label="科室名称" prop="deptId">
              <el-select v-model="form.deptId" @change="searchDeptIdDoctors" placeholder="请选择科室名称" style="width: 100%">
                <el-option
                  v-for="item in departmentsList"
                  :key="item.deptId"
                  :label="item.deptName"
                  :value="item.deptId">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="医生名称" prop="doctorId">
              <el-select v-model="form.doctorId" placeholder="请选择医生名称" style="width: 100%">
                <el-option
                  v-for="item in doctorsList"
                  :key="item.doctorId"
                  :label="item.name"
                  :value="item.doctorId">
                </el-option>
              </el-select>
            </el-form-item>

          </el-col>

        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="排班日期">
              <el-date-picker style="width: 100%" type="date" v-model="form.date" placeholder="请输入排班日期"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row >
          <el-col :span="12">
            <el-form-item label="上午开始时间">
              <el-time-select
                style="width: 100%"
                v-model="form.morningStart"
                picker-options="{start='08:30', step='00:15', end='18:30'}"
                placeholder="请输入上午开始时间">
              </el-time-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="上午结束时间">
              <el-time-select
                style="width: 100%"
                v-model="form.morningEnd"
                picker-options="{start='08:30', step='00:15', end='18:30'}"
                placeholder="请输入上午结束时间">
              </el-time-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="上午信号源数量">
              <el-input v-model="form.morningQuota" placeholder="请输入上午信号源数量"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row >
          <el-col :span="12">
            <el-form-item label="下午开始时间">
              <el-time-select
                style="width: 100%"
                v-model="form.afternoonStart"
                picker-options="{start='08:30', step='00:15', end='18:30'}"
                placeholder="请输入下午开始时间">
              </el-time-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="下午结束时间">
              <el-time-select
                style="width: 100%"
                v-model="form.afternoonEnd"
                picker-options="{start='08:30', step='00:15', end='18:30'}"
                placeholder="请输入下午结束时间">
              </el-time-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="下午信号源数量">
              <el-input v-model="form.afternoonQuota" placeholder="请输入下午信号源数量"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row >
          <el-col :span="12">
            <el-form-item label="夜间开始时间">
              <el-time-select
                style="width: 100%"
                v-model="form.nightStart"
                picker-options="{start='08:30', step='00:15', end='18:30'}"
                placeholder="请输入夜间开始时间">
              </el-time-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="夜间结束时间">
              <el-time-select
                style="width: 100%"
                v-model="form.nightEnd"
                picker-options="{start='08:30', step='00:15', end='18:30'}"
                placeholder="请输入夜间结束时间">
              </el-time-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="夜间信号源数量">
              <el-input v-model="form.nightQuota" placeholder="请输入夜间信号源数量"/>
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
import {addSchedules, delSchedules, getSchedules, listSchedules, updateSchedules} from "@/api/schedules/schedules";
import {getDepartmentsList} from "@/api/departments/departments";
import {searchDeptIdDoctorId, searchDeptIdDoctors} from "@/api/doctors/doctors";

export default {
  name: "Schedules",
  data() {
    return {
      departmentsList: [],
      //医生列表
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
      // 排班管理表格数据
      schedulesList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        doctorId: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        doctorId: [
          {required: true, message: "医生ID不能为空", trigger: "blur"}
        ],
        date: [
          {required: true, message: "排班日期不能为空", trigger: "blur"}
        ],
        morningStart: [
          {required: true, message: "上午开始时间不能为空", trigger: "blur"}
        ],
        morningEnd: [
          {required: true, message: "上午结束时间不能为空", trigger: "blur"}
        ],
        afternoonStart: [
          {required: true, message: "下午开始时间不能为空", trigger: "blur"}
        ],
        afternoonEnd: [
          {required: true, message: "下午结束时间不能为空", trigger: "blur"}
        ],
        nightStart: [
          {required: true, message: "夜间开始时间不能为空", trigger: "blur"}
        ],
        nightEnd: [
          {required: true, message: "夜间结束时间不能为空", trigger: "blur"}
        ],
        morningQuota: [
          {required: true, message: "上午号源数量不能为空", trigger: "blur"}
        ],
        afternoonQuota: [
          {required: true, message: "下午号源数量不能为空", trigger: "blur"}
        ],
        nightQuota: [
          {required: true, message: "夜间号源数量不能为空", trigger: "blur"}
        ],
        morningRemaining: [
          {required: true, message: "上午剩余号源不能为空", trigger: "blur"}
        ],
        afternoonRemaining: [
          {required: true, message: "下午剩余号源不能为空", trigger: "blur"}
        ],
        nightRemaining: [
          {required: true, message: "夜间剩余号源不能为空", trigger: "blur"}
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getDepartmentsList();
  },
  methods: {
    searchDeptIdDoctors(deptId) {
      searchDeptIdDoctors(deptId).then(response => {
        this.doctorsList = response.data;
      })
    },
    getDepartmentsList() {
      getDepartmentsList().then(response => {
        this.departmentsList = response.data;
      });
    },
    /** 查询排班管理列表 */
    getList() {
      this.loading = true;
      listSchedules(this.queryParams).then(response => {
        this.schedulesList = response.rows;
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
        scheduleId: null,
        doctorId: null,
        date: null,
        morningStart: null,
        morningEnd: null,
        afternoonStart: null,
        afternoonEnd: null,
        nightStart: null,
        nightEnd: null,
        morningQuota: null,
        afternoonQuota: null,
        nightQuota: null,
        morningRemaining: null,
        afternoonRemaining: null,
        nightRemaining: null,
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
      this.ids = selection.map(item => item.scheduleId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加排班管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const scheduleId = row.scheduleId || this.ids
      getSchedules(scheduleId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改排班管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.scheduleId != null) {
            updateSchedules(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addSchedules(this.form).then(response => {
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
      const scheduleIds = row.scheduleId || this.ids;
      this.$modal.confirm('是否确认删除排班管理编号为"' + scheduleIds + '"的数据项？').then(function () {
        return delSchedules(scheduleIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('schedules/schedules/export', {
        ...this.queryParams
      }, `schedules_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
