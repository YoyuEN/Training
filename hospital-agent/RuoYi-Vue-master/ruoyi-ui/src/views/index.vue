<template>
  <el-card>
    <el-row :gutter="20">
      <el-col :span="12">
        <el-card>
          <el-table :data="deptNameAndDate">
<!--            <el-table-column label="科室名称" prop="deptName"></el-table-column>-->
<!--            <el-table-column label="预约时间" prop="appointmentDate"></el-table-column>-->
<!--            <el-table-column label="预约数量" prop="appointmentCount"></el-table-column>-->

            <el-table-column label="科室名称">
              <template slot-scope="scope">
                <el-tag>{{ scope.row.deptName }}</el-tag>
              </template>
            </el-table-column>
            <el-table-column label="预约时间">
              <template slot-scope="scope">
                <el-tag>{{ scope.row.appointmentDate }}</el-tag>
              </template>
            </el-table-column>
            <el-table-column label="预约数量">
              <template slot-scope="scope">
                <el-tag>{{ scope.row.appointmentCount }}</el-tag>
              </template>
            </el-table-column>
          </el-table>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card>
          <el-table :data="hospitalNameAndTime">
            <el-table-column label="医院名称">
              <template slot-scope="scope">
                <el-tag>{{ scope.row.hospitalName }}</el-tag>
              </template>
            </el-table-column>
            <el-table-column label="预约时间">
              <template slot-scope="scope">
                <el-tag>{{ scope.row.timeSlot }}</el-tag>
              </template>
            </el-table-column>
            <el-table-column label="预约数量">
              <template slot-scope="scope">
                <el-tag>{{ scope.row.appointmentCount }}</el-tag>
              </template>
            </el-table-column>
          </el-table>
        </el-card>
      </el-col>

    </el-row>
<!--    <el-row :gutter="20">-->
<!--      <el-col :span="12">-->
<!--        <el-card>-->
<!--          <el-table :data="doctorNameAndDate">-->
<!--            <el-table-column label="医生名称">-->
<!--              <template slot-scope="scope">-->
<!--                <el-tag>{{ scope.row.doctorName }}</el-tag>-->
<!--              </template>-->
<!--            </el-table-column>-->
<!--            <el-table-column label="预约时间">-->
<!--              <template slot-scope="scope">-->
<!--                <el-tag>{{ scope.row.appointmentDate }}</el-tag>-->
<!--              </template>-->
<!--            </el-table-column>-->
<!--            <el-table-column label="预约数量">-->
<!--              <template slot-scope="scope">-->
<!--                <el-tag>{{ scope.row.appointmentCount }}</el-tag>-->
<!--              </template>-->
<!--            </el-table-column>-->
<!--          </el-table>-->
<!--        </el-card>-->
<!--      </el-col>-->
<!--      <el-col :span="12">-->
<!--        <el-card>-->
<!--          <el-table :data="deptNameAndTime">-->
<!--            <el-table-column label="科室名称">-->
<!--              <template slot-scope="scope">-->
<!--                <el-tag>{{ scope.row.deptName }}</el-tag>-->
<!--              </template>-->
<!--            </el-table-column>-->
<!--            <el-table-column label="预约时间">-->
<!--              <template slot-scope="scope">-->
<!--                <el-tag>{{ scope.row.timeSlot }}</el-tag>-->
<!--              </template>-->
<!--            </el-table-column>-->
<!--            <el-table-column label="预约数量">-->
<!--              <template slot-scope="scope">-->
<!--                <el-tag>{{ scope.row.appointmentCount }}</el-tag>-->
<!--              </template>-->
<!--            </el-table-column>-->
<!--          </el-table>-->
<!--        </el-card>-->
<!--      </el-col>-->
<!--    </el-row>-->
  </el-card>
</template>
<script>
import {
  countAppointmentsByDeptNameAndDate,
  countAppointmentsByHospitalNameAndTime,
  countAppointmentsByDoctorNameAndDate,
  countAppointmentsByDeptNameAndTime,
} from "@/api/appointments/appointments";

export default {
  data() {
    return {
      deptNameAndDate: [],
      doctorNameAndDate: [],
      hospitalNameAndTime: [],
      deptNameAndTime: [],
    }
  },
  created() {
    this.countAppointmentsByDeptNameAndDate();
    this.countAppointmentsByHospitalNameAndTime();
    this.countAppointmentsByDoctorNameAndDate();
    this.countAppointmentsByDeptNameAndTime();
  },
  methods: {
    countAppointmentsByDeptNameAndDate() {
      countAppointmentsByDeptNameAndDate().then(response => {
        this.deptNameAndDate = response.data;
      });
    },
    countAppointmentsByHospitalNameAndTime() {
      countAppointmentsByHospitalNameAndTime().then(response => {
        this.hospitalNameAndTime = response.data;
      });
    },
    countAppointmentsByDoctorNameAndDate() {
      countAppointmentsByDoctorNameAndDate().then(response => {
        this.doctorNameAndDate = response.data;
      });
    },
    countAppointmentsByDeptNameAndTime() {
      countAppointmentsByDeptNameAndTime().then(response => {
        this.deptNameAndTime = response.data;
      });
    },
  }
}
</script>
