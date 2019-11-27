import request from '@/utils/request'
//阿里云oss 秘钥获取接口
export function policy() {
  return request({
    url:'/aliyun/oss/policy',
    method:'get',
  })
}

//七牛云秘钥获取接口
export function qiniuToken(isPublic,key) {
  let url = '/qiniu/oss/token?bucketType='+(isPublic?'public':'private')+(key?'&key='+key:'');
  return request({
    url: url,
    method:'get'
  })
}
