const webSocket = require("ws")

let data = { port: 9001 }
const server = new webSocket.Server(data)
// 定义用于保存请求路径信息和对应客户端实例的 Map 对象
const pathToClient = new Map();

//监听消息 ws:webSocket实例 req:前端传来的数据
server.on('connection',function connection(ws, req) {
    console.log('监听消息')
    // 获取客户端传递过来的路径参数
    const path = req.url;
    // 将请求路径信息和对应客户端实例存入 Map 中
    pathToClient.set(path, ws);
    
    ws.on('message', function(msg) {
        let data = JSON.parse(msg);
        if(data.type == "heart"){
            ws.send(JSON.stringify(data))
        }else{
            // 广播消息
            broadcast(ws, JSON.stringify(data.value))
        }
        // console.log(JSON.stringify(data));
        // if(data.type == "showData"){
        //     ws.send(JSON.stringify("this is show text!"))
        // }else if(data.type == "updateData"){
        //     ws.send(JSON.stringify("this is update text!"))
        // }else if(data.type == "heart"){
        //     ws.send(JSON.stringify(data))
        // }
        
    })
})

//打开连接
server.on('open',function open() {
    console.log('打开连接')
})

//关闭连接
server.on('close',function close() {
    console.log('关闭连接')
    // 告诉所有人，有人离开
})

//连接异常
server.on('error',()=> {
    console.log('用户连接异常')
})

function broadcast(ws, msg){
    // console.log(server.clients.size);
    server.clients.forEach(client => {
        // if (client.readyState === webSocket.OPEN && client !== ws) {
        //     client.send(msg);
        // }
        if (client.readyState === webSocket.OPEN) {
            client.send(msg);
        }
    })
}