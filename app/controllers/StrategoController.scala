package controllers

import de.htwg.se.stratego.controller.GameEngine
import javax.inject._
import play.api.mvc._

@Singleton
class StrategoController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  val eng = GameEngine.engine
  def stratego = Action {
    Ok(eng.gb.toString)
  }
  def newGame = Action {
    GameEngine.engine.newGame()
    Ok(eng.gb.toString)
  }
  def set(x: Int, y: Int) = Action {
    val coords = de.htwg.se.stratego.model.boardComponent.Coordinates(x, y)
    eng.set(coords)
    Ok(eng.gb.toString)
  }
  def unset(x: Int, y: Int) = Action {
    val coords = de.htwg.se.stratego.model.boardComponent.Coordinates(x, y)
    eng.unset(coords)
    Ok(eng.gb.toString)
  }
  def select(strength: Int) = Action {
    eng.selectFigure(strength)
    Ok(eng.gb.toString + "Selected Figure with strength " + strength)
  }
  def move(x1: Int, y1: Int, x2: Int, y2: Int) = Action {
    val from = de.htwg.se.stratego.model.boardComponent.Coordinates(x1, y1)
    val to = de.htwg.se.stratego.model.boardComponent.Coordinates(x2, y2)
    eng.move(from, to)
    Ok(eng.gb.toString)
  }
}