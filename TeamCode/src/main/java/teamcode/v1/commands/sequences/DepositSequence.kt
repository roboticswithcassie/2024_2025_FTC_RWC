package teamcode.v1.commands.sequences

import com.asiankoala.koawalib.command.commands.ChooseCmd
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.command.commands.WaitCmd
import com.asiankoala.koawalib.command.commands.WaitUntilCmd
import com.asiankoala.koawalib.command.group.SequentialGroup
import teamcode.v1.commands.subsystems.ClawCmds
import teamcode.v1.constants.ArmConstants
import teamcode.v1.constants.ClawConstants
import teamcode.v1.subsystems.Arm
import teamcode.v1.subsystems.Claw
import teamcode.v1.subsystems.Lift
import teamcode.v1.subsystems.Guide

class DepositSequence(
    lift: Lift,
    arm : Arm,
    claw: Claw,
    guide : Guide,
    armAngle : Double,
    LiftHeight : Double,
    GripPos : Double,
) : SequentialGroup(
    ClawCmds.ClawCmd(claw, ClawConstants.closePos),
    InstantCmd({arm.setPos(armAngle)}, arm),
    WaitCmd(0.3),
    InstantCmd({lift.setPos(LiftHeight)}, lift),
    WaitCmd(0.1),
    InstantCmd({guide.startReading()}),
    InstantCmd({guide.setPos(GripPos)}),
    WaitUntilCmd{ guide.lastRead < 30 },
    ChooseCmd(InstantCmd({lift.setPos(LiftHeight-2)}), WaitCmd(0.0), {LiftHeight-2 > 0}),
    InstantCmd({guide.stopReading()})
)