package interviews.amazon.OA2021;

/**
 * On an infinite plane, a robot is programmed to repeat a set of movements indefinitely. The robot starts at position (0, 0) facing north. The movement can be one of three:
 *
 * "S": go straight 1 unit
 * "L": turn 90 degrees to the left
 * "R": turn 90 degrees to the right
 * Find out if there exists a circle in the plane such that the robot never leaves the circle.
 *
 * Input
 * movements: a string made up of 'S', 'L', 'R' that determines the robot's future position on the plane.
 *
 * Output
 * Return true if the robot is bounded in a circle in the plane; otherwise return false.
 *
 * Constraints
 * 1 <= movements.length <= 100
 * Examples
 * Example 1:
 * Input: "SSLLSS"
 * Output: true
 * Explanation:
 * The robot moves from (0, 0) to (0, 2), turns 180 degrees to face south, and then returns to (0, 0). The robot remains in the circle of radius 2 centered at the origin at all times.
 *
 * Example 2:
 * Input: "SS"
 * Output: false
 * Explanation:
 * The robot moves north indefinitely.
 *
 * Example 3:
 * Input: "SL"
 * Output: true
 * Explanation:
 * The robot moves from (0, 0) -> (0, 1) -> (-1, 1) -> (-1, 0) -> (0, 0) -> ..., circling around the perimeter of a square of size 1 on the plane.
 */
public class RobotBoundedInBox {

}
