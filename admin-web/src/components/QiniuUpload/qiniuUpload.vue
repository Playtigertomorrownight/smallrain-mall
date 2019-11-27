<template> 
  <div>
    <el-upload
      ref="upload"
      :action="uploadUrl"
      :data="uploadData"
      :list-type="listStyle"
      :multiple="multiple"
      :file-list="fileList"
      :before-upload="beforeUpload"
      :on-remove="handleRemove"
      :on-success="handleUploadSuccess"
      :on-preview="handlePreview"
      :limit="maxCount"
      :on-exceed="handleExceed"
    ><i class="el-icon-plus"></i>
    </el-upload>
    <el-dialog :visible.sync="dialogVisible">
      <img width="100%" :src="dialogImageUrl" alt="">
    </el-dialog>
  </div>
</template>
<script>
  import {qiniuToken} from '@/api/oss'

  export default {
    name: 'qiniuUpload',
    props: {
      //图片属性数组
      value: {
        type: Array,
        required: false,
        default: []
      },
      //是否多文件
      multiple:{
        type: Boolean,
        default: false
      },
      //最大上传图片数量
      maxCount:{
        type:Number,
        default:15
      }
    },
    data() {
      return {
        uploadUrl: "https://upload.qiniup.com",
        listStyle: this.multiply?"picture-card":"picture",
        dialogVisible: false,
        dialogImageUrl: "",
        uploadData: {
          token: "",
          config: {
            useCdnDomain: false,
            region: "qiniu.region.z0"
          }
        }
      };
    },
    computed: {
      fileList() {
        let fileList=[];
        for(let i=0;i<this.value.length;i++){
          fileList.push({url:this.value[i]});
        }
        return fileList;
      }
    },
    methods: {
      emitInput(fileList) {
        let value=[];
        for(let i=0;i<fileList.length;i++){
          value.push(fileList[i].url);
        }
        this.$emit('input', value)
      },
      handleRemove(file, fileList) {
        this.emitInput(fileList);
      },
      handlePreview(file) {
        this.dialogVisible = true;
        this.dialogImageUrl=file.url;
      },
      beforeUpload(file) {   //上传前，获取token
        let _self = this;
        let fileName = null;
        return new Promise((resolve, reject) => {
          qiniuToken().then(response => {
            _self.uploadData.token = response.data;
            resolve(true)
          }).catch(err => {
            console.log(err)
            reject(false)
          })
        })
      },
      handleUploadSuccess(res, file) {
        this.fileList.push({url: file.name,url:this.dataObj.host + '/' + this.dataObj.dir + '/' + file.name});
        this.emitInput(this.fileList);
      },
      handleExceed(files, fileList) {
        this.$message({
          message: '最多只能上传'+this.maxCount+'张图片',
          type: 'warning',
          duration:1000
        });
      },
    }
  }
</script>
<style>

</style>


