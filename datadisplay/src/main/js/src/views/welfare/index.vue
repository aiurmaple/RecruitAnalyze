<template>
  <div class="mixin-components-container">
    <el-row>
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>职业类别</span>
        </div>
        <div style="margin-bottom:50px;">
          <el-col :span="3" class="text-center" v-for="button in buttons" :key="button.id">
            <button :class="button.color" v-on:click="handleData(button.id)" >{{button.legend}}</button>
          </el-col>
        </div>
      </el-card>
    </el-row>
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <welfare-word-cloud :chart-data="lineChartData"/>
    </el-row>
  </div>
</template>

<script>
  /* eslint-disable */
  import WelfareWordCloud from '@/components/charts/WelfareWordCloud'

  export default {
    name: 'Welfare',
    components: {
      WelfareWordCloud
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
          ]
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
          for( var i = 0; i < res.data.length; i++) {
            var object = {};
            object['id'] = res.data[i].id;
            object['legend'] = res.data[i].jobLabel;
            object['color'] = "pan-btn " +  this.colors[i % this.colors.length];
            vm.$set(this.buttons, i, object);
          }
          this.handleData(this.buttons[0].id);
        });
      },
      handleData(index) {
        this.$store.dispatch('getWelfareNumByJob', {jobNameId:index}).then((res) => {
          var chartData = [];
          for (var i = 0; i < res.data.length; i++) {
            var object = {};
            object["name"] = res.data[i].welfareLabel;
            object["value"] = res.data[i].count;
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
</style>
