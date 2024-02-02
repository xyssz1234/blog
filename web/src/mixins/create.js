const create = {
    data() {
        return {
            tableData: [],
            pageInfo: {
                pageNum: 1,
                pageSize: 5,
                total: 0,
            },
            api: {
                pageUrl: "",
                saveUrl: "",
                deleteUrl: ""
            },
            searchForm: {},
            isDrawerDialog: false,
            statusList: [
                {
                    value: "启用"
                }, {
                    value: '禁用'
                }
            ]

        }
    },
    created() {
        this.getAll()
    },
    methods: {
        handleCurrentChange(e) {
            this.pageInfo.pageNum = e;
            this.getAll();
        },
        handleSizeChange(e) {
            this.pageInfo.pageSize = e
            this.getAll();
        },
        getAll() {
            let data = Object.assign(this.searchForm, this.pageInfo)
            this.$axios.post(this.api.pageUrl, data).then(res => {
                //console.log(res)
                if (res.code === 200) {
                    this.tableData = res.data.records
                    this.pageInfo.total = res.data.total
                }
            })
        },
        closeDrawer(e) {
            this.isDrawerDialog = e
            this.formData = {}
        },
        submitDrawer(e) {
            this.formData = e
            //console.log(e)
            this.submitNewForm()
            this.formData = {}
        },
        handleEdit(index, row) {
            this.isDrawerDialog = true;
            this.formData = Object.assign({}, row);
            //console.log(index, row);
        },
        handleDelete(index, row) {
            // console.log(row.id);
            this.ids = [row.id];
            this.batchDelete();
        },
        submitNewForm() {
            this.$axios.post(this.api.saveUrl, this.formData).then(res => {
                //console.log(res)
                if (res.code === 200) {
                    this.$message({
                        message: res.msg,
                        type: "success"
                    })
                    this.isDrawerDialog = false
                    this.getAll()
                    //this.cancelForm()
                }
            })
        },
        batchDelete() {
            this.$confirm(`此操作将永久删除id为[${this.ids}], 是否继续?`, '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning',
                center: true
            }).then(() => {

                this.$axios.post(this.api.deleteUrl, this.ids).then(res => {
                    //console.log(res)
                    if (res.code === 200) {
                        this.$message({
                            message: res.msg,
                            type: "success"
                        })
                        this.pageInfo.pageNum = 1
                        this.getAll()
                    }
                })


            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消删除'
                });
                this.$refs.multipleTable.clearSelection();
            });
        },
    }

}

export default create;