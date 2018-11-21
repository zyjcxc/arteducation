var token = getQiniuVideoToken();
var domain = getDomain();
var config = {
    useCdnDomain: true,
    disableStatisticsReport: false,
    retryCount: 6,
    region: qiniu.region.z0
};
var putExtra = {
    fname: "",
    params: {},
    mimeType: ["avi", "mp4", "wmv", "flv"]
};
imageControl(domain);
uploadWithOthers(token, putExtra, config, domain);

function getDomain() {
    $.ajax({
        type: 'get',
        url: "/qiniu/domain",
        async: false,
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            domain = data;
        }
    });
    return domain;
}
