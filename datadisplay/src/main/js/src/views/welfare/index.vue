<template>
  <div class="mixin-components-container">
    <el-row>
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>职业类别</span>
        </div>
        <div style="margin-bottom:50px;">
          <el-col :span="3" class="text-center">
            <button class="pan-btn blue-btn" >Java</button>
          </el-col>
          <el-col :span="3" class="text-center">
            <button class="pan-btn light-blue-btn" >Android</button>
          </el-col>
          <el-col :span="3" class="text-center">
            <button class="pan-btn pink-btn" >PHP</button>
          </el-col>
          <el-col :span="3" class="text-center">
            <button class="pan-btn green-btn" >Python</button>
          </el-col>
          <el-col :span="3" class="text-center">
            <button class="pan-btn tiffany-btn" >Web前端</button>
          </el-col>
          <el-col :span="3" class="text-center">
            <button class="pan-btn yellow-btn" >人工智能</button>
          </el-col>
          <el-col :span="3" class="text-center">
            <button class="pan-btn pink-btn" >大数据</button>
          </el-col>
          <el-col :span="3" class="text-center">
            <button class="pan-btn blue-btn" >算法</button>
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
        lineChartData: null,
        types: ['Java','Android', 'PHP', 'Python', 'Web', 'AI', 'BigData', 'Arithmetic'],
        welfares: ['五险一金', '补充医疗保险', '定期体检', '节日福利', '员工旅游', '项目奖金'
        , '绩效奖金', '年底双薪', '交通补助', '餐补', '全勤奖', '带薪年假']
      }
    },
    mounted() {
      this.initChart()
    },
    methods: {
      initChart() {
        this.lineChartData = {
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
        }

        //初始化chatData
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
