package studio.papercube.capturity

import studio.papercube.capturity.services.AutoCaptureService
import studio.papercube.library.argparser.Parameter
import java.io.File

fun main(args: Array<String>) {
    val p = Parameter.resolve(args)
    val interval = p.getSingleValue("-i")?.toInt()
    val autoPause = p.getSingleValue("-autopause")?.toInt()
    val destinationDir = File(p.getSingleValue("-dest") ?: "Screenshots")
    val captureSvc = AutoCaptureService()
    captureSvc.interval = interval ?: 10000
    captureSvc.pauseThreshold = autoPause ?: 0
    captureSvc.destinationDirectory = destinationDir
    captureSvc.start()
}

