<template>
  <d2-container :class="{'page-compact':crud.pageOptions.compact}">
    <template slot="header">固定列
      <example-helper title="帮助"  >
        <h4>请点击右下角查看本页源码</h4>
      </example-helper>

    </template>
    <d2-crud-x
        ref="d2Crud"
        v-bind="_crudProps"
        v-on="_crudListeners"
        @customHandleBtn="customHandleBtnHandle"
    >
      <div slot="header">
        <crud-search ref="search" :options="crud.searchOptions" @submit="handleSearch"  />
        <el-button size="small" type="primary" @click="addRow"><i class="el-icon-plus"/> 新增</el-button>
        <crud-toolbar :search.sync="crud.searchOptions.show"
                      :compact.sync="crud.pageOptions.compact"
                      :columns="crud.columns"
                      @refresh="doRefresh()"
                      @columns-filter-changed="handleColumnsFilterChanged"/>
      </div>

      <template slot="expandSlot" slot-scope="scope">
        这里显示行展开数据:{{scope.row.data}}
      </template>

    </d2-crud-x>
  </d2-container>
</template>

<script>
import { AddObj, GetList, UpdateObj, DelObj } from './api'
import { crudOptions } from './crud'
import { d2CrudPlus } from 'd2-crud-plus'
import helper from './helper'
export default {
  name: 'formFix',
  components: {},
  mixins: [d2CrudPlus.crud],
  data () {
    return {
      helper: helper
    }
  },
  computed: {
  },
  mounted () { // 将初始化推迟到mounted
    this._doStart()
  },
  methods: {
    _OnCreated () {
      // 覆盖为空方法
    },
    getCrudOptions () {
      return crudOptions
    },
    pageRequest (query) {
      return GetList(query)
    },
    addRequest (row) {
      return AddObj(row)
    },
    updateRequest (row) {
      return UpdateObj(row)
    },
    delRequest (row) {
      return DelObj(row.id)
    },
    handleCurrentChange (currentRow, oldCurrentRow) {
      this.$message('单选事件：' + currentRow.data)
    },
    handleSelectionChange (selection) {
      console.log('多选数据：', selection)
    },
    customHandleBtnHandle ({ index, row }) {
      this.$message('自定义操作按钮：' + row.data + ',index:' + index)
    }
  }
}
</script>
