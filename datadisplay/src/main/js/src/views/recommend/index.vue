<template>
  <div class="mixin-components-container">
    <el-row>
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>职位推荐</span>
          <div class="right-menu">
            <button id="recommend" class="recommend-btn blue-btn" v-on:click="recommendClick()" >推荐</button>
          </div>
        </div>
        <div style="margin-bottom:50px;" class="components-container board">
          <Kanban :key="1" :list="list1" :group="group" class="kanban todo" header-text="选择感兴趣标签" />
          <Kanban :key="2" :list="list2" :group="group" class="kanban working" header-text="匹配标签处" />
        </div>
      </el-card>
    </el-row>
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <recommend-cartogram :chart-data="lineChartData" />
    </el-row>
  </div>
</template>

<script>
  import Kanban from '@/components/Kanban'
  import RecommendCartogram from "../../components/charts/RecommendCartogram";

  export default {
    name: 'Recommend',
    components: {
      Kanban,
      RecommendCartogram
    },
    data() {
      return {
        jobSum: 0,
        group: 'mission',
        list1: [
          { name: '学历要求低', id: 2, action: 'getEduLevelRanking' },
          { name: '招聘热门', id: 3, action: 'getJobRanking' },
          { name: '热门城市热门', id: 4, action: 'getJobRankingByCity' },
          { name: '发展潜力大', id: 5, action: 'getJobRankingByWorkingExp' }
        ],
        list2: [
          { name: '薪资高', id: 1, action: 'getSalaryRanking' },
        ],
        sumData:{},
        lineChartData: {
          chartData: [
            {value:335, name:'算法工程师'},
            {value:310, name:'人工智能'},
            {value:274, name:'Web前端'},
            {value:235, name:'Java开发'},
            {value:400, name:'PHP'},
            {value:200, name:'大数据'},
            {value:325, name:'Python'},
            {value:215, name:'Android'},
          ],
        },
      }
    },
    mounted() {
      this.initChart()
    },
    methods: {
      initChart() {
        this.$store.dispatch('getJobsName').then((res) => {
          this.jobSum = res.data.length;
          for (var i = 0; i < res.data.length; i++) {
            var object = {value:0};
            object.name = res.data[i].jobLabel;
            this.sumData[res.data[i].id] = object;
          }
        });
      },
      recommendClick() {
        this.initChart()
        for (var i = 0; i < this.list2.length; i++) {
           var action = this.list2[i].action;
          this.$store.dispatch(action).then((res) => {
            for (var j = 0; j < res.data.length; j++) {
              this.sumData[res.data[j]].value += (j + 1)
            }
            var chartData = [];
            for (var z = 0; z < this.jobSum; z++) {
              chartData[z] = this.sumData[z+1];
            }
            this.lineChartData.chartData = chartData;
          });
        }
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
  .board {
    width: 1000px;
    margin-left: 20px;
    display: flex;
    justify-content: space-around;
    flex-direction: row;
    align-items: flex-start;
  }

  .components-container {
    margin: 30px 50px;
    position: relative;
  }

  .kanban {
    &.todo {
      .board-column-header {
        background: #4A9FF9;
      }
    }
    &.working {
      .board-column-header {
        background: #f9944a;
      }
    }
    &.done {
      .board-column-header {
        background: #2ac06d;
      }
    }
  }
  .right-menu {
    float: right;
    height: 100%;
  }

  .recommend-btn {
    font-size: 14px;
    color: #fff;
    padding: 14px 36px;
    border-radius: 8px;
    border: none;
    outline: none;
    transition: 600ms ease all;
    position: relative;
    display: inline-block;
    margin-bottom: 5px;
    &:hover {
      background: #fff;
      cursor:pointer;

      &:before,
      &:after {
        width: 100%;
        transition: 600ms ease all;
      }
    }

    &:before,
    &:after {
      content: '';
      position: absolute;
      top: 0;
      right: 0;
      height: 2px;
      width: 0;
      transition: 400ms ease all;
    }

    &::after {
      right: inherit;
      top: inherit;
      left: 0;
      bottom: 0;
    }
  }
</style>
