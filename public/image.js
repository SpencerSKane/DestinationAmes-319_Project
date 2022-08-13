const res = require('express/lib/response');

exports.uploadImage = async (base64) => {

    var axios = require('axios');
    var FormData = require('form-data');
    var data = new FormData();
    data.append('image', base64);

    var config = {
        method: 'post',
        url: 'https://api.imgbb.com/1/upload?&key=89dd7ea2a13a379525e7f6b8d3e59ca7',
        headers: {
            ...data.getHeaders()
        },
        data: data
    };
    try {
        const resp = await axios(config)
        //console.log("aa")
        return resp.data.data.image.url;
    } catch (err) {
        console.error(err)

    }
}

exports.getImage = async (url) => {
    var axios = require('axios');

    var config = {
        method: 'get',
        url: url,
        headers: {}
    };

    axios(config)
        .then(function (response) {
            console.log(JSON.stringify(response.data));
        })
        .catch(function (error) {
            console.log(error);
        });

};