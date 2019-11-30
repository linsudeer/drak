import Request from '@/utils/request'

export default  {
    listByCategory: (categoryId) => {
        return Request({
            url: '/articles/category/'+categoryId,
            method: 'get'
        })
    },
    publish: (params, articleId) => {
        return Request({
            url: '/publish/'+articleId,
            method: 'post',
            data:params
        })
    },
    draft: (params, articleId) => {
        const url = articleId?("/draft/"+articleId):"/draft";
        return Request({
            url: url,
            method: 'post',
            data:params
        })
    },
    detail:(articleId) => {
        return Request({
            url: '/articles/'+articleId,
            method: 'get'
        })
    },
    uploadImage:(formdata) => {
        return Request({
            url: '/article/uploadImage',
            method: 'post',
            data: formdata,
            headers: { 'Content-Type': 'multipart/form-data' }
        })
    },
    getTagsByName: (name, maxCnt) => {
        return Request({
            url: '/tags/'+name,
            params: {
                maxCnt:maxCnt
            },
            method: 'get'
        })
    },
    getCategorys: () => {
        return Request({
            url: '/categorys'
        })
    }
}