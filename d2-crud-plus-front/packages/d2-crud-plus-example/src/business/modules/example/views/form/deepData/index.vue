<template>
  <d2-container  :class="{'page-compact':crud.pageOptions.compact}">
    <template slot="header">
      多级数据
      <span style="color:gray;font-size: 12px">数据嵌套object的处理{ id: 1, user: { name: '小红' , gender: 2 } }，【配置点号key即可，key:'user.name'】 </span>
    </template>
    <d2-crud-x
        ref="d2Crud"
        v-bind="_crudProps"
        v-on="_crudListeners"
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
    </d2-crud-x>
  </d2-container>
</template>

<script>
import { AddObj, GetList, UpdateObj, DelObj } from './api'
import { crudOptions } from './crud'
import { d2CrudPlus } from 'd2-crud-plus'
export default {
  name: 'formView',
  mixins: [d2CrudPlus.crud],
  data () {
    return {
      show: true
    }
  },
  mounted () {
    console.log('dict cache：', d2CrudPlus.util.dict.getCache())
  },
  methods: {
    getCrudOptions () {
      return crudOptions(this)
    },
    async pageRequest (query) {
      const ret = await GetList(query)
      //  ret.data.records 可以随意修改
      return ret
    },
    addRequest (row) {
      return AddObj(row)
    },
    updateRequest (row) {
      return UpdateObj(row)
    },
    delRequest (row) {
      return DelObj(row.id)
    }
  }
}
</script>
