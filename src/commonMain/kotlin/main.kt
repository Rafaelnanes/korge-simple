import korlibs.event.*
import korlibs.image.color.*
import korlibs.korge.*
import korlibs.korge.box2d.*
import korlibs.korge.scene.*
import korlibs.korge.view.*
import korlibs.korge.view.collision.*
import korlibs.math.geom.*
import org.jbox2d.dynamics.*

suspend fun main() = Korge(windowSize = Size(600, 600), backgroundColor = Colors["#2b2b2b"]) {
    val sceneContainer = sceneContainer()

    sceneContainer.changeTo { MyScene() }
}

class MyScene : Scene() {
    override suspend fun SContainer.sceneMain() {

        val playerSpeed = 5.0;
        val player = solidRect(30, 30, color = Colors.BLUE).registerBodyWithFixture(
            type = BodyType.KINEMATIC,
            density = 2,
            friction = 0.01
        )
        player.xy(0, 0)

        val enemy1 = solidRect(30, 30, color = Colors.RED).registerBodyWithFixture(
            type = BodyType.STATIC,
            friction = 0.2,
            restitution = 0.2
        )
        enemy1.xy(100, 100)

        player.addUpdater {
            val keys = views.input.keys
            val lastX = x
            val lastY = y

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

            if (collidesWith(enemy1)) {
                y = lastY
                x = lastX
            }

        }

    }
}

