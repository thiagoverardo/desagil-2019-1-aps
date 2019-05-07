package br.pro.hashi.ensino.desagil.aps.model;

public class HalfAdder extends Gate {

    private final NandGate nandLeft;
    private final NandGate nandTop;
    private final NandGate nandBottom;
    private final NandGate nandRight;
    private final NandGate nandLeft2;
    private final NandGate nandRight2;


    public HalfAdder() {
        super("HALFADDER", 2, 2);

        nandLeft = new NandGate();
        nandTop = new NandGate();

        nandTop.connect(1, nandLeft);

        nandBottom = new NandGate();

        nandBottom.connect(0, nandLeft);

        nandRight = new NandGate();

        nandRight.connect(0, nandTop);
        nandRight.connect(1, nandBottom);

        nandRight2 = new NandGate();
        nandLeft2 = new NandGate();

        nandRight2.connect(0, nandLeft2);
        nandRight2.connect(1, nandLeft2);

    }


    @Override
    public boolean read(int outputPin) {
        if (outputPin != 0) {
            throw new IndexOutOfBoundsException(outputPin);
        }
        return nandRight.read() && nandRight2.read();
    }

    @Override
    public void connect(int inputPin, SignalEmitter emitter) {
        switch (inputPin) {
            case 0:
                nandTop.connect(0, emitter);
                nandLeft.connect(0, emitter);
                nandLeft2.connect(0, emitter);

                break;
            case 1:
                nandLeft.connect(1, emitter);
                nandBottom.connect(1, emitter);
                nandLeft2.connect(1, emitter);

                break;
            default:
                throw new IndexOutOfBoundsException(inputPin);
        }
    }
}
