from pyniryo2 import *

robot = NiryoRobot("169.254.200.200")

robot.arm.calibrate_auto()

# robot.move_joints(0.2, -0.3, 0.1, 0.0, 0.5, -0.8)

robot.arm.move_joints([-1.356, -0.237, -0.899, 0.169, -0.063, 0.034]) #safe

robot.tool.release_with_tool()

robot.arm.move_joints([ -1.339, -0.426, -0.797, 0.192, -0.066, -0.007]) # pick

robot.tool.grasp_with_tool()

robot.arm.move_joints([-1.356, -0.237, -0.899, 0.169, -0.063, 0.034]) #safe

robot.arm.move_joints([-0.311, -0.545, -0.769, 0.198, -0.196, -0.549])

robot.tool.release_with_tool()

# robot.close_connection() 