<template>
  <div class="mixin-components-container">
    <el-row>
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>城市类别</span>
          <div class="right-menu">
            <search id="header-search" class="right-menu-item" />
          </div>
        </div>
        <div style="margin-bottom:50px;">
          <el-col :span="3" class="text-center" v-for="button in buttons" :key="button.id">
            <button :class="button.color" v-on:click="handleData(button.id)" >{{button.legend}}</button>
          </el-col>
        </div>
      </el-card>
    </el-row>
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <job-cartogram :chart-data="lineChartData"/>
    </el-row>
  </div>
</template>

<script>
  /* eslint-disable */
  import JobCartogram from "../../components/charts/JobCartogram";
  import Search from "../../components/HeaderSearch"

  export default {
    name: 'Job',
    components: {
      JobCartogram,
      Search
    },
    data() {
      return {
        lineChartData: {
          chartData: [ {
            name: '五险一金',
            value: 10000,
          },
            {
              name: '补充医疗保险',
              value: 6181
            },
            {
              name: '定期体检',
              value: 4386
            },
            {
              name: '节日福利',
              value: 4055
            },
            {
              name: '员工旅游',
              value: 2467
            },
            {
              name: '项目奖金',
              value: 2244
            },
            {
              name: '绩效奖金',
              value: 1898
            },
            {
              name: '年底双薪',
              value: 1484
            },
            {
              name: '交通补助',
              value: 1112
            },
            {
              name: '餐补',
              value: 965
            },
            {
              name: '全勤奖',
              value: 847
            },
            {
              name: '带薪年假',
              value: 582
            },
          ],
          legends:[],
          selected:{}
        },
        buttons: [
          {legend: "Java", id:1, color:"pan-btn blue-btn"},
          {legend: "Android", id:2, color:"pan-btn blue-btn"}
        ],
        colors: ["blue-btn","light-blue-btn","pink-btn","green-btn","tiffany-btn","yellow-btn"]
      }
    },
    mounted() {
      this.initChart()
    },
    methods: {
      initChart() {
        //初始化
        this.$store.dispatch('getJobsName').then((res) => {
          for (var i = 0; i < res.data.length; i++) {
            this.lineChartData.legends[i] = res.data[i].jobLabel;
          }
          // 初始化selected属性
          for (var i = 8; i< this.lineChartData.legends.length; i++) {
            var str = this.lineChartData.legends[i];
            this.lineChartData.selected[str] = false
          }
          // 初始化button
          this.$store.dispatch('getCitys').then((res) => {
            for (var i = 0; i < res.data.length; i++) {
              var object = {};
              object['id'] = res.data[i].id;
              object['legend'] = res.data[i].cityLabel;
              object['color'] = "pan-btn " +  this.colors[i % this.colors.length];
              vm.$set(this.buttons, i, object);
            }
            this.handleData(this.buttons[0].id);
          });
        });
      },
      handleData(index) {
        this.$store.dispatch('getJobsNumByCityId', {cityId:index}).then((res) => {
          var chartData = [];
          for (var i = 0; i < res.data.length; i++) {
            var object = {};
            object["name"] = this.lineChartData.legends[i];
            object["value"] = res.data[i];
            chartData[i] = object;
          }
          this.lineChartData.chartData = chartData;
        })
      }
    }
  }
</script>

<style scoped>
  .mixin-components-container {
    background-color: #f0f2f5;
    padding: 30px;
    min-height: calc(100vh - 84px);
  }
</style>

<style rel="stylesheet/scss" lang="scss" scoped>
  @import '../../styles/btn';

  .text-center {
    text-align: center
  }

  .right-menu {
    float: right;
    height: 100%;

    &:focus {
      outline: none;
    }

    .right-menu-item {
      display: inline-block;
      padding: 0 8px;
      height: 100%;
      font-size: 18px;
      color: #5a5e66;
      vertical-align: text-bottom;

      &.hover-effect {
        cursor: pointer;
        transition: background .3s;

        &:hover {
          background: rgba(0, 0, 0, .025)
        }
      }
    }

    .avatar-container {
      margin-right: 30px;

      .avatar-wrapper {
        margin-top: 5px;
        position: relative;

        .user-avatar {
          cursor: pointer;
          width: 40px;
          height: 40px;
          border-radius: 10px;
        }

        .el-icon-caret-bottom {
          cursor: pointer;
          position: absolute;
          right: -20px;
          top: 25px;
          font-size: 12px;
        }
      }
    }
  }
</style>
