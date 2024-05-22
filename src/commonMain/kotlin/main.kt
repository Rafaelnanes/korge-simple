import korlibs.event.*
import korlibs.image.color.*
import korlibs.korge.*
import korlibs.korge.scene.*
import korlibs.korge.view.*
import korlibs.math.geom.*

suspend fun main() = Korge(windowSize = Size(600, 600), backgroundColor = Colors["#2b2b2b"]) {
    val sceneContainer = sceneContainer()

    sceneContainer.changeTo { MyScene() }
}

class MyScene : Scene() {
    override suspend fun SContainer.sceneMain() {

        val playerSpeed = 10.0;

        val player = solidRect(30, 30, color = Colors.BLUE)
        player.xy(0, 0)
        player.addUpdater {

            val keys = views.input.keys
            if (keys[Key.D]) {
                x += playerSpeed
            }

            if (keys[Key.A]) {
                x -= playerSpeed
            }

            if (keys[Key.W]) {
                y -= playerSpeed
            }

            if (keys[Key.S]) {
                y += playerSpeed
            }
        }
        val enemy1 = solidRect(30, 30, color = Colors.RED)
        enemy1.xy(100, 100)

    }
}

