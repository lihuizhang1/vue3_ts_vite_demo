const webSocket = require("ws")

let data = { port: 9001 }
const server = new webSocket.Server(data)

//打开连接
server.on('open',function open() {
    console.log('打开连接')
})

//关闭连接
server.on('close',function close() {
    console.log('关闭连接')
})

//监听消息 ws:webSocket实例 req:前端传来的数据
server.on('connection',function connection(ws, req) {
    console.log('监听消息')
    ws.on('message', function(msg) {
        let data = JSON.parse(msg);
        console.log(data);
        if(data.type == "showData"){
            ws.send(JSON.stringify("this is show text!"))
        }else if(data.type == "updateData"){
            ws.send(JSON.stringify("this is update text!"))
        }
        
    })
})
