package com.github.szsascha.assvrmapclient

import java.net.Socket
import java.io.PrintWriter
import java.io.InputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder

class App {

    /**
     * 
     * Resolve the port of an IBM i server with as-svrmap
     * 
     * @throws Exception Server not found
     * @return Port of IBM i server as Int
     * 
     */
    fun getServerPort(host: String, server: String, serverMapperPort: Int = 449): Int {
        // Connect to as-svrmap and send server to resolve the port of
        val socket = Socket(host, serverMapperPort)
        socket.outputStream.write(server.toByteArray(Charsets.UTF_8))
        
        // Read response from server
        val byteArray = ByteArray(5)
        socket.inputStream.read(byteArray, 0, 5)

        socket.close()

        // Put byte array into ByteBuffer because it's easier to work with
        val bb: ByteBuffer = ByteBuffer.wrap(byteArray)

        // Get the first byte (status) from response. Only 0x2b is valid. Everything else is an error
        val status: Byte = bb.get(0)
        if (status.toInt() != 0x2b) throw Exception("Server '${server}' not found on '${host}'! ")
        
        // Go to pos 3. It's where we can find the port
        bb.position(3)
        val port: Short = bb.getShort()

        // Return port as integer
        return port.toInt()
    }

}

fun main() {
    println(App().getServerPort("pub400.com", "as-svrmap"))
}
