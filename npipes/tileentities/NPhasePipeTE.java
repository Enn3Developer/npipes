package npipes.tileentities;

import buildcraft.transport.Pipe;
import buildcraft.transport.TileGenericPipe;
import dan200.computer.api.IComputerAccess;
import dan200.computer.api.IHostedPeripheral;
import npipes.pipes.NPhasedPipe;

public class NPhasePipeTE extends TileGenericPipe implements IHostedPeripheral {
    private final String[] methods = new String[]{"help", "getFreq", "setFreq", "canReceive", "toggleReceive"};

    @Override
    public void initialize(Pipe pipe) {
        super.initialize(pipe);
        if (pipe instanceof NPhasedPipe) {
            System.out.println("Works");
        }
    }

    @Override
    public void update() {
    }

    @Override
    public String getType() {
        return "NPhasedPipe";
    }

    @Override
    public String[] getMethodNames() {
        return methods;
    }

    @Override
    public Object[] callMethod(IComputerAccess p0, int p1, Object[] p2) throws Exception {
        if (p1 >= 0 && p1 < methods.length && this.pipe instanceof NPhasedPipe) {
            NPhasedPipe pipe = (NPhasedPipe) this.pipe;
            switch (p1) {
                case 0: // help
                    return new Object[]{"help() -> string - shows this message\n" +
                            "getFreq() -> int - returns the frequency\n" +
                            "setFreq(int) -> string - sets the frequency\n" +
                            "canReceive() -> boolean - returns if it can receive\n" +
                            "toggleReceive() -> string - toggles if it can receive\n"
                    };
                case 1: // getFreq
                    return new Object[]{pipe.logic.getFrequency()};
                case 2: // setFreq
                    if (p2.length != 1 || !(p2[0] instanceof Double)) {
                        System.out.println(p2[0].getClass());
                        return new Object[]{"Error calling this method"};
                    }
                    pipe.logic.setFrequency((int) Math.ceil((Double) p2[0]));
                    return new Object[]{"success"};
                case 3: // canReceive
                    return new Object[]{pipe.logic.canReceive};
                case 4: // toggleReceive
                    pipe.logic.canReceive = !pipe.logic.canReceive;
                    return new Object[]{"success"};
            }
        }

        return new Object[]{"Error method doesn't exist"};
    }

    @Override
    public boolean canAttachToSide(int p0) {
        return true;
    }

    @Override
    public void attach(IComputerAccess p0) {
        System.out.println("attached");
    }

    @Override
    public void detach(IComputerAccess p0) {

    }
}
