<template>
  <div class="app-container">
    <div class="filter-container" style="margin-bottom: 30px">
      <el-input v-model="listQuery.name" class="filter-item" placeholder="请输入角色名称" style="width: 200px"
                clearable></el-input>
      <el-button type="primary" icon="el-icon-search" @click="handleFilter">查询</el-button>
      <el-button type="primary" icon="el-icon-edit" @click="handleCreate">添加</el-button>
    </div>

    <div>
      <el-table v-loading="isLoading"
                element-loading-text="正在查询中。。"
                :data="list" border fit highlight-current-row stripe>
        <el-table-column align="center" label="角色ID" prop="id" sortable width="100px"></el-table-column>
        <el-table-column align="center" label="角色名称" prop="name"></el-table-column>
        <el-table-column align="center" label="角色说明" prop="desc"></el-table-column>
        <el-table-column align="center" label="操作">
          <template slot-scope="scope">
            <el-button type="primary" size="mini" icon="el-icon-edit" @click="handleUpdate(scope.row)">编辑</el-button>
            <el-button type="danger" size="mini" icon="el-icon-delete" @click="handleDelete(scope.row)">删除</el-button>
          </template>

        </el-table-column>
      </el-table>
    </div>

    <div>
      <el-dialog :title="dialogType" :visible.sync="dialogVisible">
        <el-form :model="dialogForm"   :rules="rules"  ref="dialogForm" status-icon label-width="100px" style="width: 400px; margin-left:50px;">
          <el-form-item label="角色名称" prop="name" :label-width="dialogFormLabelWidth" >
            <el-input v-model="dialogForm.name" clearable></el-input>
          </el-form-item>
          <el-form-item label="角色描述" :label-width="dialogFormLabelWidth">
            <el-input v-model="dialogForm.desc" clearable></el-input>
          </el-form-item>
        </el-form>

        <div slot="footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button v-if="dialogType == '编辑'" type="primary" @click="updateData">确定</el-button>
          <el-button v-else type="primary" @click="createData">确定</el-button>
        </div>
      </el-dialog>

    </div>

    <div>
      <el-dialog title="删除" :visible.sync="deleteDialogVisible" width="30%">
        <span>确认删除【{{deleteRowInfo.name}}】？</span>

        <span slot="footer" class="dialog-foolter">
          <el-button @click="deleteDialogVisible = false">取消</el-button>
          <el-button type="danger" @click="confirmDelete">确定</el-button>
        </span>
      </el-dialog>
    </div>

  </div>
</template>

<script>

  import {listRole, updateRole, deleteRole} from "@/api/role";
  const dialogTypeArr = ['创建', '编辑'];
  export default {
    name: 'index',
    data() {

      return {
        dialogType: undefined,
        deleteDialogVisible:　false,
        dialogFormLabelWidth: '100px',
        dialogVisible: false,
        isLoading: undefined,
        list: null,
        listQuery: {
          name: undefined,
        },
        dialogForm: {
          id:　'',
          name: '',
          desc: ''
        },
        rules: {
          name: [
            {required: true, message: '角色名称不可以为空', trigger: 'blur'},
            {min: 3, max: 8, message: '角色名称3-8个字符', trigger: 'blur'}

          ]
        },
        deleteRowInfo: {}
      }
    },
    created() {
      this.getList();
    },
    methods: {
      getList() {
        this.isLoading = true
        listRole(this.listQuery).then(response => {
          this.list = response.data
          // alert("111")
          // alert( this.list[0].id)
          // alert(this.list)
          this.isLoading =false

        }).catch(e => {
            console.log(e)
            this.list = []
            this.isLoading = false
        })
      },
      handleFilter() {
        this.getList();
      },
      handleUpdate(row) {
        this.dialogType = dialogTypeArr[1]
        this.dialogVisible=true
        // alert(row.id)
        this.dialogForm = Object.assign({}, row)
        // alert(this.dialogForm.id)
        // alert(this.dialogForm.name)
        this.$nextTick(() =>{
          this.$refs['dialogForm'].clearValidate()
        });
      },
      updateData() {
        this.$refs['dialogForm'].validate(valid => {
          // alert(valid)
          if(valid) {
            updateRole(this.dialogForm).then(response =>{
              for(let v of this.list){
                if(v.id === this.dialogForm.id) {
                  let index = this.list.indexOf(v)
                  this.list.splice(index, 1, this.dialogForm)
                  break
                }
              }
              this.dialogVisible = false
              this.$notify.success({
                title: '成功',
                message: '更新成功'
              });



              // this.getList()
            }).catch(e =>{
              console.log(e)
              this.dialogVisible = false
              this.$notify.error({
                title: '失败',
                message: '更新失败'
              })
            })
          }
        })
      },

      handleDelete(row) {
        Object.assign(this.deleteRowInfo, row)
        this.deleteDialogVisible = true
      },
      confirmDelete() {
        // alert(this.deleteRowInfo.id + ";" + this.deleteRowInfo.name)
        for(let v of this.list) {
          if(v.id === this.deleteRowInfo.id) {
            this.list.splice(this.list.indexOf(v), 1)
            break;
          }
        }

        this.deleteDialogVisible = false
        this.$notify.success({
          title: "成功",
          message: "删除成功！"
        })
      /*  deleteRole(this.deleteRowInfo).then(response => {
          this.deleteDialogVisible = false
          this.$notify.success({
            title: "成功",
            message: "删除成功！"
          })
        }).catch(e => {
          console.log(e)
          this.deleteDialogVisible = false
          this.$notify.error({
            title: "失败",
            message: "删除失败"
          })
        })*/
      },
      handleCreate() {
        this.dialogType = dialogTypeArr[0]
        this.dialogVisible = true

        this.dialogForm = {
          id: undefined,
          name: undefined,
          desc: undefined
        }
        this.$nextTick(() =>{
          this.$refs['dialogForm'].clearValidate()
        })
      },
      createData() {

      }

    }
  }
</script>

<style scoped>

</style>
