/**
 * 
 */
package io.github.markbernard.jcbm64;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author Mark Bernard
 *
 */
public class Cpu {
    public static final byte NEGATIVE_FLAG = (byte)0x80;
    public static final byte OVERFLOW_FLAG = (byte)0x40;
    public static final byte BREAK_FLAG = (byte)0x10;
    public static final byte DECIMAL_FLAG = (byte)0x08;
    public static final byte INTERRUPT_DISABLE_FLAG = (byte)0x04;
    public static final byte ZERO_FLAG = (byte)0x02;
    public static final byte CARRY_FLAG = (byte)0x01;
    
    private int programCounter;
    private byte stackPointer;
    private byte xRegister;
    private byte yRegister;
    private byte accumulator;
    private byte processorStatus;
    private int addressBus;
    private final Memory memory = Memory.instance;

    public Cpu() {
        reset();
    }
    
    public void reset() {
        stackPointer = (byte)0xff;
        programCounter = 0xfce2;
        xRegister = (byte)0xff;
        processorStatus = (byte)0xb4;
    }
    public int next() {
        int cycles = 0;
        byte opcode = memory.read(programCounter);
        programCounter++;
        
        switch(opcode & 0xf0) {
            case 0x00:
                switch(opcode & 0x0f) {
                    case 0x00: cycles = op00(); break;
                    case 0x01: cycles = op01(); break;
                    case 0x02: cycles = op02(); break;
                    case 0x03: cycles = op03(); break;
                    case 0x04: cycles = op04(); break;
                    case 0x05: cycles = op05(); break;
                    case 0x06: cycles = op06(); break;
                    case 0x07: cycles = op07(); break;
                    case 0x08: cycles = op08(); break;
                    case 0x09: cycles = op09(); break;
                    case 0x0a: cycles = op0a(); break;
                    case 0x0b: cycles = op0b(); break;
                    case 0x0c: cycles = op0c(); break;
                    case 0x0d: cycles = op0d(); break;
                    case 0x0e: cycles = op0e(); break;
                    case 0x0f: cycles = op0f(); break;
                }
                break;
            case 0x10:
                switch(opcode & 0x0f) {
                    case 0x00: cycles = op10(); break;
                    case 0x01: cycles = op11(); break;
                    case 0x02: cycles = op12(); break;
                    case 0x03: cycles = op13(); break;
                    case 0x04: cycles = op14(); break;
                    case 0x05: cycles = op15(); break;
                    case 0x06: cycles = op16(); break;
                    case 0x07: cycles = op17(); break;
                    case 0x08: cycles = op18(); break;
                    case 0x09: cycles = op19(); break;
                    case 0x0a: cycles = op1a(); break;
                    case 0x0b: cycles = op1b(); break;
                    case 0x0c: cycles = op1c(); break;
                    case 0x0d: cycles = op1d(); break;
                    case 0x0e: cycles = op1e(); break;
                    case 0x0f: cycles = op1f(); break;
                }
                break;
            case 0x20:
                switch(opcode & 0x0f) {
                    case 0x00: cycles = op20(); break;
                    case 0x01: cycles = op21(); break;
                    case 0x02: cycles = op22(); break;
                    case 0x03: cycles = op23(); break;
                    case 0x04: cycles = op24(); break;
                    case 0x05: cycles = op25(); break;
                    case 0x06: cycles = op26(); break;
                    case 0x07: cycles = op27(); break;
                    case 0x08: cycles = op28(); break;
                    case 0x09: cycles = op29(); break;
                    case 0x0a: cycles = op2a(); break;
                    case 0x0b: cycles = op2b(); break;
                    case 0x0c: cycles = op2c(); break;
                    case 0x0d: cycles = op2d(); break;
                    case 0x0e: cycles = op2e(); break;
                    case 0x0f: cycles = op2f(); break;
                }
                break;
            case 0x30:
                switch(opcode & 0x0f) {
                    case 0x00: cycles = op30(); break;
                    case 0x01: cycles = op31(); break;
                    case 0x02: cycles = op32(); break;
                    case 0x03: cycles = op33(); break;
                    case 0x04: cycles = op34(); break;
                    case 0x05: cycles = op35(); break;
                    case 0x06: cycles = op36(); break;
                    case 0x07: cycles = op37(); break;
                    case 0x08: cycles = op38(); break;
                    case 0x09: cycles = op39(); break;
                    case 0x0a: cycles = op3a(); break;
                    case 0x0b: cycles = op3b(); break;
                    case 0x0c: cycles = op3c(); break;
                    case 0x0d: cycles = op3d(); break;
                    case 0x0e: cycles = op3e(); break;
                    case 0x0f: cycles = op3f(); break;
                }
                break;
            case 0x40:
                switch(opcode & 0x0f) {
                    case 0x00: cycles = op40(); break;
                    case 0x01: cycles = op41(); break;
                    case 0x02: cycles = op42(); break;
                    case 0x03: cycles = op43(); break;
                    case 0x04: cycles = op44(); break;
                    case 0x05: cycles = op45(); break;
                    case 0x06: cycles = op46(); break;
                    case 0x07: cycles = op47(); break;
                    case 0x08: cycles = op48(); break;
                    case 0x09: cycles = op49(); break;
                    case 0x0a: cycles = op4a(); break;
                    case 0x0b: cycles = op4b(); break;
                    case 0x0c: cycles = op4c(); break;
                    case 0x0d: cycles = op4d(); break;
                    case 0x0e: cycles = op4e(); break;
                    case 0x0f: cycles = op4f(); break;
                }
                break;
            case 0x50:
                switch(opcode & 0x0f) {
                    case 0x00: cycles = op50(); break;
                    case 0x01: cycles = op51(); break;
                    case 0x02: cycles = op52(); break;
                    case 0x03: cycles = op53(); break;
                    case 0x04: cycles = op54(); break;
                    case 0x05: cycles = op55(); break;
                    case 0x06: cycles = op56(); break;
                    case 0x07: cycles = op57(); break;
                    case 0x08: cycles = op58(); break;
                    case 0x09: cycles = op59(); break;
                    case 0x0a: cycles = op5a(); break;
                    case 0x0b: cycles = op5b(); break;
                    case 0x0c: cycles = op5c(); break;
                    case 0x0d: cycles = op5d(); break;
                    case 0x0e: cycles = op5e(); break;
                    case 0x0f: cycles = op5f(); break;
                }
                break;
            case 0x60:
                switch(opcode & 0x0f) {
                    case 0x00: cycles = op60(); break;
                    case 0x01: cycles = op61(); break;
                    case 0x02: cycles = op62(); break;
                    case 0x03: cycles = op63(); break;
                    case 0x04: cycles = op64(); break;
                    case 0x05: cycles = op65(); break;
                    case 0x06: cycles = op66(); break;
                    case 0x07: cycles = op67(); break;
                    case 0x08: cycles = op68(); break;
                    case 0x09: cycles = op69(); break;
                    case 0x0a: cycles = op6a(); break;
                    case 0x0b: cycles = op6b(); break;
                    case 0x0c: cycles = op6c(); break;
                    case 0x0d: cycles = op6d(); break;
                    case 0x0e: cycles = op6e(); break;
                    case 0x0f: cycles = op6f(); break;
                }
                break;
            case 0x70:
                switch(opcode & 0x0f) {
                    case 0x00: cycles = op70(); break;
                    case 0x01: cycles = op71(); break;
                    case 0x02: cycles = op72(); break;
                    case 0x03: cycles = op73(); break;
                    case 0x04: cycles = op74(); break;
                    case 0x05: cycles = op75(); break;
                    case 0x06: cycles = op76(); break;
                    case 0x07: cycles = op77(); break;
                    case 0x08: cycles = op78(); break;
                    case 0x09: cycles = op79(); break;
                    case 0x0a: cycles = op7a(); break;
                    case 0x0b: cycles = op7b(); break;
                    case 0x0c: cycles = op7c(); break;
                    case 0x0d: cycles = op7d(); break;
                    case 0x0e: cycles = op7e(); break;
                    case 0x0f: cycles = op7f(); break;
                }
                break;
            case 0x80:
                switch(opcode & 0x0f) {
                    case 0x00: cycles = op80(); break;
                    case 0x01: cycles = op81(); break;
                    case 0x02: cycles = op82(); break;
                    case 0x03: cycles = op83(); break;
                    case 0x04: cycles = op84(); break;
                    case 0x05: cycles = op85(); break;
                    case 0x06: cycles = op86(); break;
                    case 0x07: cycles = op87(); break;
                    case 0x08: cycles = op88(); break;
                    case 0x09: cycles = op89(); break;
                    case 0x0a: cycles = op8a(); break;
                    case 0x0b: cycles = op8b(); break;
                    case 0x0c: cycles = op8c(); break;
                    case 0x0d: cycles = op8d(); break;
                    case 0x0e: cycles = op8e(); break;
                    case 0x0f: cycles = op8f(); break;
                }
                break;
            case 0x90:
                switch(opcode & 0x0f) {
                    case 0x00: cycles = op90(); break;
                    case 0x01: cycles = op91(); break;
                    case 0x02: cycles = op92(); break;
                    case 0x03: cycles = op93(); break;
                    case 0x04: cycles = op94(); break;
                    case 0x05: cycles = op95(); break;
                    case 0x06: cycles = op96(); break;
                    case 0x07: cycles = op97(); break;
                    case 0x08: cycles = op98(); break;
                    case 0x09: cycles = op99(); break;
                    case 0x0a: cycles = op9a(); break;
                    case 0x0b: cycles = op9b(); break;
                    case 0x0c: cycles = op9c(); break;
                    case 0x0d: cycles = op9d(); break;
                    case 0x0e: cycles = op9e(); break;
                    case 0x0f: cycles = op9f(); break;
                }
                break;
            case 0xa0:
                switch(opcode & 0x0f) {
                    case 0x00: cycles = opa0(); break;
                    case 0x01: cycles = opa1(); break;
                    case 0x02: cycles = opa2(); break;
                    case 0x03: cycles = opa3(); break;
                    case 0x04: cycles = opa4(); break;
                    case 0x05: cycles = opa5(); break;
                    case 0x06: cycles = opa6(); break;
                    case 0x07: cycles = opa7(); break;
                    case 0x08: cycles = opa8(); break;
                    case 0x09: cycles = opa9(); break;
                    case 0x0a: cycles = opaa(); break;
                    case 0x0b: cycles = opab(); break;
                    case 0x0c: cycles = opac(); break;
                    case 0x0d: cycles = opad(); break;
                    case 0x0e: cycles = opae(); break;
                    case 0x0f: cycles = opaf(); break;
                }
                break;
            case 0xb0:
                switch(opcode & 0x0f) {
                    case 0x00: cycles = opb0(); break;
                    case 0x01: cycles = opb1(); break;
                    case 0x02: cycles = opb2(); break;
                    case 0x03: cycles = opb3(); break;
                    case 0x04: cycles = opb4(); break;
                    case 0x05: cycles = opb5(); break;
                    case 0x06: cycles = opb6(); break;
                    case 0x07: cycles = opb7(); break;
                    case 0x08: cycles = opb8(); break;
                    case 0x09: cycles = opb9(); break;
                    case 0x0a: cycles = opba(); break;
                    case 0x0b: cycles = opbb(); break;
                    case 0x0c: cycles = opbc(); break;
                    case 0x0d: cycles = opbd(); break;
                    case 0x0e: cycles = opbe(); break;
                    case 0x0f: cycles = opbf(); break;
                }
                break;
            case 0xc0:
                switch(opcode & 0x0f) {
                    case 0x00: cycles = opc0(); break;
                    case 0x01: cycles = opc1(); break;
                    case 0x02: cycles = opc2(); break;
                    case 0x03: cycles = opc3(); break;
                    case 0x04: cycles = opc4(); break;
                    case 0x05: cycles = opc5(); break;
                    case 0x06: cycles = opc6(); break;
                    case 0x07: cycles = opc7(); break;
                    case 0x08: cycles = opc8(); break;
                    case 0x09: cycles = opc9(); break;
                    case 0x0a: cycles = opca(); break;
                    case 0x0b: cycles = opcb(); break;
                    case 0x0c: cycles = opcc(); break;
                    case 0x0d: cycles = opcd(); break;
                    case 0x0e: cycles = opce(); break;
                    case 0x0f: cycles = opcf(); break;
                }
                break;
            case 0xd0:
                switch(opcode & 0x0f) {
                    case 0x00: cycles = opd0(); break;
                    case 0x01: cycles = opd1(); break;
                    case 0x02: cycles = opd2(); break;
                    case 0x03: cycles = opd3(); break;
                    case 0x04: cycles = opd4(); break;
                    case 0x05: cycles = opd5(); break;
                    case 0x06: cycles = opd6(); break;
                    case 0x07: cycles = opd7(); break;
                    case 0x08: cycles = opd8(); break;
                    case 0x09: cycles = opd9(); break;
                    case 0x0a: cycles = opda(); break;
                    case 0x0b: cycles = opdb(); break;
                    case 0x0c: cycles = opdc(); break;
                    case 0x0d: cycles = opdd(); break;
                    case 0x0e: cycles = opde(); break;
                    case 0x0f: cycles = opdf(); break;
                }
                break;
            case 0xe0:
                switch(opcode & 0x0f) {
                    case 0x00: cycles = ope0(); break;
                    case 0x01: cycles = ope1(); break;
                    case 0x02: cycles = ope2(); break;
                    case 0x03: cycles = ope3(); break;
                    case 0x04: cycles = ope4(); break;
                    case 0x05: cycles = ope5(); break;
                    case 0x06: cycles = ope6(); break;
                    case 0x07: cycles = ope7(); break;
                    case 0x08: cycles = ope8(); break;
                    case 0x09: cycles = ope9(); break;
                    case 0x0a: cycles = opea(); break;
                    case 0x0b: cycles = opeb(); break;
                    case 0x0c: cycles = opec(); break;
                    case 0x0d: cycles = oped(); break;
                    case 0x0e: cycles = opee(); break;
                    case 0x0f: cycles = opef(); break;
                }
                break;
            case 0xf0:
                switch(opcode & 0x0f) {
                    case 0x00: cycles = opf0(); break;
                    case 0x01: cycles = opf1(); break;
                    case 0x02: cycles = opf2(); break;
                    case 0x03: cycles = opf3(); break;
                    case 0x04: cycles = opf4(); break;
                    case 0x05: cycles = opf5(); break;
                    case 0x06: cycles = opf6(); break;
                    case 0x07: cycles = opf7(); break;
                    case 0x08: cycles = opf8(); break;
                    case 0x09: cycles = opf9(); break;
                    case 0x0a: cycles = opfa(); break;
                    case 0x0b: cycles = opfb(); break;
                    case 0x0c: cycles = opfc(); break;
                    case 0x0d: cycles = opfd(); break;
                    case 0x0e: cycles = opfe(); break;
                    case 0x0f: cycles = opff(); break;
                }
                break;
        }
        
        return cycles;
    }

    /** BRK */
    private final int op00() {
        push(processorStatus);
        push(readProgramCounter());
        setFlag((byte)(BREAK_FLAG | INTERRUPT_DISABLE_FLAG));
        programCounter = (memory.read(0xfffe) & 0xff) | ((memory.read(0xffff) << 8) & 0xff00);

        return 7;
    }
    /** ORA */
    private final int op01() {
        loadIndirectX();
        ora();
        
        return 6; 
    }
    /** JAM Illegal command */
    private final int op02() { return 0; }
    /** SLO Illegal command */
    private final int op03() {
        loadIndirectX();
        byte data = memory.read(addressBus);
        data = (byte)(data << 1);
        memory.write(addressBus, data);
        if((data & 0x80) == 0x80) {
            setCarry();
        }
        else {
            clearCarry();
        }
        accumulator |= data;
        checkZeroNegative(accumulator);
        
        return 8;
    }
    /** NOP Illegal command */
    private final int op04() {
        return 3;
    }
    /** ORA */
    private final int op05() {
        loadZeroPage();
        ora();
        
        return 3; 
    }
    /** ASL */
    private final int op06() {
        loadZeroPage();
        asl();
        
        return 5;
    }
    /** Illegal command */
    private final int op07() { return 0; }
    /** PHP */
    private final int op08() {
        push(processorStatus);
        
        return 3;
    }
    /** ORA */
    private final int op09() {
        loadImmediate();
        ora();
        
        return 2; 
    }
    /** ASL */
    private final int op0a() {
        accumulator = performAsl(accumulator);
        
        return 2;
    }
    /** Illegal command */
    private final int op0b() { return 0; }
    /** NOP Illegal command */
    private final int op0c() {
        return 4;
    }
    /** ORA */
    private final int op0d() {
        loadAbsolute();
        ora();
        
        return 4; 
    }
    /** ASL */
    private final int op0e() {
        loadAbsolute();
        asl();
        
        return 5;
    }
    /** Illegal command */
    private final int op0f() { return 0; }

    /** BPL */
    private final int op10() {
        byte data = readProgramCounter();
        if((processorStatus & NEGATIVE_FLAG) == 0x00) {
            programCounter += data;
        }
        
        return 2;
    }
    /** ORA */
    private final int op11() {
        loadIndirectY();
        ora();
        
        return 5; 
    }
    /** JAM Illegal command */
    private final int op12() { return 0; }
    /** Illegal command */
    private final int op13() { return 0; }
    /** NOP Illegal command */
    private final int op14() {
        return 4;
    }
    /** ORA */
    private final int op15() {
        loadZeroPageX();
        ora();
        
        return 4; 
    }
    /** ASL */
    private final int op16() {
        loadZeroPageX();
        asl();
        
        return 6;
    }
    /** Illegal command */
    private final int op17() { return 0; }
    /** CLC */
    private final int op18() {
        clearCarry();
        
        return 2;
    }
    /** ORA */
    private final int op19() {
        loadAbsoluteY();
        ora();
        
        return 4; 
    }
    /** NOP Illegal command */
    private final int op1a() {
        return 2;
    }
    /** Illegal command */
    private final int op1b() { return 0; }
    /** NOP Illegal command */
    private final int op1c() {
        return 4;
    }
    /** ORA */
    private final int op1d() {
        loadAbsoluteX();
        ora();
        
        return 4; 
    }
    /** ASL */
    private final int op1e() {
        loadAbsoluteX();
        asl();
        
        return 7;
    }
    /** Illegal command */
    private final int op1f() { return 0; }

    /** JSR */
    private final int op20() {
        loadAbsolute();
        programCounter--;
        push((byte)((programCounter >> 8) & 0xff));
        push((byte)(programCounter & 0xff));
        programCounter = addressBus;
        
        return 6;
    }
    /** AND */
    private final int op21() {
        loadIndirectX();
        and();
        
        return 6;
    }
    /** JAM Illegal command */
    private final int op22() { return 0; }
    /** Illegal command */
    private final int op23() { return 0; }
    /** BIT */
    private final int op24() {
        loadZeroPage();
        bit();
        
        return 3;
    }
    /** AND */
    private final int op25() {
        loadZeroPage();
        and();
        
        return 3;
    }
    /** ROL */
    private final int op26() {
        loadZeroPage();
        rol();
        
        return 5;
    }
    /** Illegal command */
    private final int op27() { return 0; }
    /** PLP */
    private final int op28() {
        processorStatus = pop();
        
        return 4;
    }
    /** AND */
    private final int op29() {
        loadImmediate();
        and();
        
        return 2;
    }
    /** ROL */
    private final int op2a() {
        accumulator = performRol(accumulator);
        
        return 2;
    }
    /** Illegal command */
    private final int op2b() { return 0; }
    /** BIT */
    private final int op2c() {
        loadZeroPage();
        bit();
        
        return 4;
    }
    /** AND */
    private final int op2d() {
        loadAbsolute();
        and();
        
        return 4;
    }
    /** ROL */
    private final int op2e() {
        loadAbsolute();
        rol();
        
        return 6;
    }
    /** Illegal command */
    private final int op2f() { return 0; }

    /** BMI */
    private final int op30() {
        byte data = readProgramCounter();
        if((processorStatus & NEGATIVE_FLAG) == NEGATIVE_FLAG) {
            programCounter += data;
        }
        
        return 2;
    }
    /** AND */
    private final int op31() {
        loadIndirectY();
        and();
        
        return 5;
    }
    /** JAM Illegal command */
    private final int op32() { return 0; }
    /** Illegal command */
    private final int op33() { return 0; }
    /** NOP Illegal command */
    private final int op34() {
        return 4;
    }
    /** AND */
    private final int op35() {
        loadZeroPageX();
        and();
        
        return 4;
    }
    /** ROL */
    private final int op36() {
        loadZeroPageX();
        rol();
        
        return 6;
    }
    /** Illegal command */
    private final int op37() { return 0; }
    /** SEC */
    private final int op38() {
        setCarry();
        
        return 2;
    }
    /** AND */
    private final int op39() {
        loadAbsoluteY();
        and();
        
        return 4;
    }
    /** NOP Illegal command */
    private final int op3a() {
        return 2;
    }
    /** Illegal command */
    private final int op3b() { return 0; }
    /** NOP Illegal command */
    private final int op3c() {
        return 4;
    }
    /** AND */
    private final int op3d() {
        loadAbsoluteX();
        and();
        
        return 4;
    }
    /** ROL */
    private final int op3e() {
        loadAbsoluteX();
        rol();
        
        return 7;
    }
    /** Illegal command */
    private final int op3f() { return 0; }

    /** RTI */
    private final int op40() {
        processorStatus = pop();
        programCounter = ((pop() & 0xff) | ((pop() & 0xff) << 8));
        // TODO check programCounter++; ??
        
        return 6;
    }
    /** EOR */
    private final int op41() {
        loadIndirectX();
        eor();
        
        return 6;
    }
    /** JAM Illegal command */
    private final int op42() { return 0; }
    /** Illegal command */
    private final int op43() { return 0; }
    /** NOP Illegal command */
    private final int op44() {
        return 3;
    }
    /** EOR */
    private final int op45() {
        loadZeroPage();
        eor();
        
        return 3;
    }
    /** LSR */
    private final int op46() {
        loadZeroPage();
        lsr();
        
        return 5;
    }
    /** Illegal command */
    private final int op47() { return 0; }
    /** PHA */
    private final int op48() {
        push(accumulator);
        
        return 3;
    }
    /** EOR */
    private final int op49() {
        addressBus = programCounter;
        programCounter++;
        eor();
        
        return 2;
    }
    /** LSR */
    private final int op4a() {
        accumulator = performLsr(accumulator);
        
        return 2;
    }
    /** Illegal command */
    private final int op4b() { return 0; }
    /** JMP */
    private final int op4c() {
        loadAbsolute();
        programCounter = addressBus;
        
        return 3;
    }
    /** EOR */
    private final int op4d() {
        loadAbsolute();
        eor();
        
        return 4;
    }
    /** LSR */
    private final int op4e() {
        loadAbsolute();
        lsr();
        
        return 6;
    }
    /** Illegal command */
    private final int op4f() { return 0; }

    /** BVC */
    private final int op50() {
        byte data = readProgramCounter();
        if((processorStatus & OVERFLOW_FLAG) == 0x00) {
            programCounter += data;
        }
        
        return 2;
    }
    /** EOR */
    private final int op51() {
        loadIndirectY();
        eor();
        
        return 5;
    }
    /** JAM Illegal command */
    private final int op52() { return 0; }
    /** Illegal command */
    private final int op53() { return 0; }
    /** NOP Illegal command */
    private final int op54() {
        return 4;
    }
    /** EOR */
    private final int op55() {
        loadZeroPageX();
        eor();
        
        return 4;
    }
    /** LSR */
    private final int op56() {
        loadZeroPageX();
        lsr();
        
        return 6;
    }
    /** Illegal command */
    private final int op57() { return 0; }
    /** CLI */
    private final int op58() {
        clearInterruptDisable();
        
        return 2;
    }
    /** EOR */
    private final int op59() {
        loadAbsoluteY();
        eor();
        
        return 4;
    }
    /** NOP Illegal command */
    private final int op5a() {
        return 2;
    }
    /** Illegal command */
    private final int op5b() { return 0; }
    /** NOP Illegal command */
    private final int op5c() {
        return 4;
    }
    /** EOR */
    private final int op5d() {
        loadAbsoluteX();
        eor();
        
        return 4;
    }
    /** LSR */
    private final int op5e() {
        loadAbsoluteX();
        lsr();
        
        return 7;
    }
    /** Illegal command */
    private final int op5f() { return 0; }

    /** RTS */
    private final int op60() {
        programCounter = ((pop() & 0xff) | ((pop() & 0xff) << 8));
        programCounter++;
        
        return 6;
    }
    /** ADC */
    private final int op61() {
        loadIndirectX();
        adc();
        
        return 6;
    }
    /** JAM Illegal command */
    private final int op62() { return 0; }
    /** Illegal command */
    private final int op63() { return 0; }
    /** NOP Illegal command */
    private final int op64() {
        return 3;
    }
    /** ADC */
    private final int op65() {
        loadZeroPage();
        adc();
        
        return 3;
    }
    /** ROR */
    private final int op66() {
        loadZeroPage();
        ror();
        
        return 5;
    }
    /** Illegal command */
    private final int op67() { return 0; }
    /** PLA */
    private final int op68() {
        accumulator = pop();
        
        return 4;
    }
    /** ADC */
    private final int op69() {
        loadImmediate();
        adc();
        
        return 2;
    }
    /** ROR */
    private final int op6a() {
        accumulator = performRor(accumulator);
        
        return 2;
    }
    /** Illegal command */
    private final int op6b() { return 0; }
    /** JMP */
    private final int op6c() {
        loadAbsoluteIndirect();
        programCounter = addressBus;
        
        return 5;
    }
    /** ADC */
    private final int op6d() {
        loadAbsolute();
        adc();
        
        return 4;
    }
    /** ROR */
    private final int op6e() {
        loadAbsolute();
        ror();
        
        return 6;
    }
    /** Illegal command */
    private final int op6f() { return 0; }

    /** BVS */
    private final int op70() {
        byte data = readProgramCounter();
        if((processorStatus & OVERFLOW_FLAG) == OVERFLOW_FLAG) {
            programCounter += data;
        }
        
        return 2;
    }
    /** ADC */
    private final int op71() {
        loadIndirectY();
        adc();
        
        return 5;
    }
    /** JAM Illegal command */
    private final int op72() { return 0; }
    /** Illegal command */
    private final int op73() { return 0; }
    /** NOP Illegal command */
    private final int op74() {
        return 4;
    }
    /** ADC */
    private final int op75() {
        loadZeroPageX();
        adc();
        
        return 4;
    }
    /** ROR */
    private final int op76() {
        loadZeroPageX();
        ror();
        
        return 6;
    }
    /** Illegal command */
    private final int op77() { return 0; }
    /** SEI */
    private final int op78() {
        setInterruptDisable();
        
        return 2;
    }
    /** ADC */
    private final int op79() {
        loadAbsoluteY();
        adc();
        
        return 4;
    }
    /** NOP Illegal command */
    private final int op7a() {
        return 2;
    }
    /** Illegal command */
    private final int op7b() { return 0; }
    /** NOP Illegal command */
    private final int op7c() {
        return 4;
    }
    /** ADC */
    private final int op7d() {
        loadAbsoluteX();
        adc();
        
        return 4;
    }
    /** ROR */
    private final int op7e() {
        loadAbsoluteX();
        ror();
        
        return 7;
    }
    /** Illegal command */
    private final int op7f() { return 0; }

    /** NOP Illegal command */
    private final int op80() {
        return 2;
    }
    /** STA */
    private final int op81() {
        loadIndirectX();
        sta();
        
        return 6;
    }
    /** NOP Illegal command */
    private final int op82() {
        return 2;
    }
    /** Illegal command */
    private final int op83() { return 0; }
    /** STY */
    private final int op84() {
        loadZeroPage();
        sty();
        
        return 3;
    }
    /** STA */
    private final int op85() {
        loadZeroPage();
        sta();
        
        return 3;
    }
    /** STX */
    private final int op86() {
        loadZeroPage();
        stx();
        
        return 3;
    }
    /** Illegal command */
    private final int op87() { return 0; }
    /** DEY */
    private final int op88() {
        yRegister = (byte)((yRegister & 0xff) - 1);
        
        return 2;
    }
    /** NOP Illegal command */
    private final int op89() {
        return 2;
    }
    /** TXA */
    private final int op8a() {
        accumulator = xRegister;
        checkZeroNegative(accumulator);
        
        return 2;
    }
    /** Illegal command */
    private final int op8b() { return 0; }
    /** STY */
    private final int op8c() {
        loadAbsolute();
        sty();
        
        return 4;
    }
    /** STA */
    private final int op8d() {
        loadAbsolute();
        sta();
        
        return 4;
    }
    /** STX */
    private final int op8e() {
        loadAbsolute();
        stx();
        
        return 4;
    }
    /** Illegal command */
    private final int op8f() { return 0; }

    /** BCC */
    private final int op90() {
        byte data = readProgramCounter();
        if((processorStatus & CARRY_FLAG) == 0x00) {
            programCounter += (data & 0xff);
        }
        
        return 2;
    }
    /** STA */
    private final int op91() {
        loadIndirectY();
        sta();
        
        return 6;
    }
    /** JAM Illegal command */
    private final int op92() { return 0; }
    /** Illegal command */
    private final int op93() { return 0; }
    /** STY */
    private final int op94() {
        loadZeroPageX();
        sty();
        
        return 4;
    }
    /** STA */
    private final int op95() {
        loadZeroPageX();
        sta();
        
        return 4;
    }
    /** STX */
    private final int op96() {
        loadZeroPageY();
        stx();
        
        return 4;
    }
    /** Illegal command */
    private final int op97() { return 0; }
    /** TAY */
    private final int op98() {
        accumulator = yRegister;
        checkZeroNegative(accumulator);
        
        return 2;
    }
    /** STA */
    private final int op99() {
        loadAbsoluteY();
        sta();
        
        return 5;
    }
    /** TXS */
    private final int op9a() {
        stackPointer = xRegister;
        
        return 2;
    }
    /** Illegal command */
    private final int op9b() { return 0; }
    /** Illegal command */
    private final int op9c() { return 0; }
    /** STA */
    private final int op9d() {
        loadAbsoluteX();
        sta();
        
        return 5;
    }
    /** Illegal command */
    private final int op9e() { return 0; }
    /** Illegal command */
    private final int op9f() { return 0; }

    /** LDY */
    private final int opa0() {
        loadImmediate();
        ldy();
        
        return 2;
    }
    /** LDA */
    private final int opa1() {
        loadIndirectX();
        lda();
        
        return 6;
    }
    /** LDX */
    private final int opa2() {
        loadImmediate();
        ldx();
        
        return 2;
    }
    /** Illegal command */
    private final int opa3() { return 0; }
    /** LDY */
    private final int opa4() {
        loadZeroPage();
        ldy();
        
        return 3;
    }
    /** LDA */
    private final int opa5() {
        loadZeroPage();
        lda();
        
        return 3;
    }
    /** LDX */
    private final int opa6() {
        loadZeroPage();
        ldx();
        
        return 3;
    }
    /** Illegal command */
    private final int opa7() { return 0; }
    /** TAY */
    private final int opa8() {
        yRegister = accumulator;
        checkZeroNegative(yRegister);
        
        return 2;
    }
    /** LDA */
    private final int opa9() {
        loadImmediate();
        lda();
        
        return 2;
    }
    /** TAX */
    private final int opaa() {
        xRegister = accumulator;
        checkZeroNegative(xRegister);
        
        return 2;
    }
    /** Illegal command */
    private final int opab() { return 0; }
    /** LDY */
    private final int opac() {
        loadAbsolute();
        ldy();
        
        return 4;
    }
    /** LDA */
    private final int opad() {
        loadAbsolute();
        lda();
        
        return 4;
    }
    /** LDX */
    private final int opae() {
        loadAbsolute();
        ldx();
        
        return 4;
    }
    /** Illegal command */
    private final int opaf() { return 0; }

    /** BCS */
    private final int opb0() {
        byte data = readProgramCounter();
        if((processorStatus & CARRY_FLAG) == CARRY_FLAG) {
            programCounter += data;
        }
        
        return 2;
    }
    /** LDA */
    private final int opb1() {
        loadIndirectY();
        lda();
        
        return 5;
    }
    /** JAM Illegal command */
    private final int opb2() { return 0; }
    /** Illegal command */
    private final int opb3() { return 0; }
    /** LDY */
    private final int opb4() {
        loadZeroPageX();
        ldy();
        
        return 4;
    }
    /** LDA */
    private final int opb5() {
        loadZeroPageX();
        lda();
        
        return 4;
    }
    /** LDX */
    private final int opb6() {
        loadZeroPageY();
        ldx();
        
        return 4;
    }
    /** Illegal command */
    private final int opb7() { return 0; }
    /** CLV */
    private final int opb8() {
        clearOverflow();
        
        return 2;
    }
    /** LDA */
    private final int opb9() {
        loadAbsoluteY();
        lda();
        
        return 4;
    }
    /** TSX */
    private final int opba() {
        xRegister = stackPointer;
        checkZeroNegative(xRegister);
        
        return 2;
    }
    /** Illegal command */
    private final int opbb() { return 0; }
    /** LDY */
    private final int opbc() {
        loadAbsoluteX();
        ldy();
        
        return 4;
    }
    /** LDA */
    private final int opbd() {
        loadAbsoluteX();
        lda();
        
        return 4;
    }
    /** LDX */
    private final int opbe() {
        loadAbsoluteY();
        ldx();
        
        return 4;
    }
    /** Illegal command */
    private final int opbf() { return 0; }

    /** CPY */
    private final int opc0() {
        performCompare(yRegister, readProgramCounter());
        
        return 2;
    }
    /** CMP */
    private final int opc1() {
        loadIndirectX();
        cmp();
        
        return 6;
    }
    /** NOP Illegal command */
    private final int opc2() {
        return 2;
    }
    /** Illegal command */
    private final int opc3() { return 0; }
    /** CPY */
    private final int opc4() {
        loadZeroPage();
        cpy();
        
        return 3;
    }
    /** CMP */
    private final int opc5() {
        loadZeroPage();
        cmp();
        
        return 3;
    }
    /** DEC */
    private final int opc6() {
        loadZeroPage();
        dec();
        
        return 5;
    }
    /** Illegal command */
    private final int opc7() { return 0; }
    /** INY */
    private final int opc8() {
        yRegister = (byte)(yRegister + 1);
        
        return 2;
    }
    /** CMP */
    private final int opc9() {
        performCompare(accumulator, readProgramCounter());
        
        return 2;
    }
    /** DEX */
    private final int opca() {
        xRegister = (byte)((xRegister & 0xff) - 1);
        
        return 2;
    }
    /** Illegal command */
    private final int opcb() { return 0; }
    /** CPY */
    private final int opcc() {
        loadAbsolute();
        cpy();
        
        return 4;
    }
    /** CMP */
    private final int opcd() {
        loadAbsolute();
        cmp();
        
        return 4;
    }
    /** DEC */
    private final int opce() {
        loadAbsolute();
        dec();
        
        return 6;
    }
    /** Illegal command */
    private final int opcf() { return 0; }

    /** BNE */
    private final int opd0() {
        byte data = readProgramCounter();
        if((processorStatus & ZERO_FLAG) == 0x00) {
            programCounter += data;
        }
        
        return 2;
    }
    /** CMP */
    private final int opd1() {
        loadIndirectY();
        cmp();
        
        return 5;
    }
    /** JAM Illegal command */
    private final int opd2() { return 0; }
    /** Illegal command */
    private final int opd3() { return 0; }
    /** NOP Illegal command */
    private final int opd4() {
        return 4;
    }
    /** CMP */
    private final int opd5() {
        loadZeroPageX();
        cmp();
        
        return 4;
    }
    /** DEC */
    private final int opd6() {
        loadZeroPageX();
        dec();
        
        return 6;
    }
    /** Illegal command */
    private final int opd7() { return 0; }
    /** CLD */
    private final int opd8() {
        clearDecimal();
        
        return 2;
    }
    /** CMP */
    private final int opd9() {
        loadAbsoluteY();
        cmp();
        
        return 4;
    }
    /** NOP Illegal command */
    private final int opda() {
        return 2;
    }
    /** Illegal command */
    private final int opdb() { return 0; }
    /** NOP Illegal command */
    private final int opdc() {
        return 4;
    }
    /** CMP */
    private final int opdd() {
        loadAbsoluteX();
        cmp();
        
        return 4;
    }
    /** DEC */
    private final int opde() {
        loadAbsoluteX();
        dec();
        
        return 7;
    }
    /** Illegal command */
    private final int opdf() { return 0; }

    /** CPX */
    private final int ope0() {
        performCompare(xRegister, readProgramCounter());
        
        return 2;
    }
    /** SBC */
    private final int ope1() {
        loadIndirectX();
        sbc();
        
        return 6;
    }
    /** NOP Illegal command */
    private final int ope2() {
        return 2;
    }
    /** Illegal command */
    private final int ope3() { return 0; }
    /** CPX */
    private final int ope4() {
        loadZeroPage();
        cpx();
        
        return 3;
    }
    /** SBC */
    private final int ope5() {
        loadZeroPage();
        sbc();
        
        return 3;
    }
    /** INC */
    private final int ope6() {
        loadZeroPage();
        inc();
        
        return 5;
    }
    /** Illegal command */
    private final int ope7() { return 0; }
    /** INX */
    private final int ope8() {
        xRegister = (byte)(xRegister + 1);
        
        return 2;
    }
    /** SBC */
    private final int ope9() {
        loadImmediate();
        sbc();
        
        return 2;
    }
    /** NOP */
    private final int opea() {
        return 2;
    }
    /** SBC  Illegal command */
    private final int opeb() {
        loadImmediate();
        sbc();
        
        return 2;
    }
    /** CPX */
    private final int opec() {
        loadAbsolute();
        cpx();
        
        return 4;
    }
    /** SBC */
    private final int oped() {
        loadAbsolute();
        sbc();
        
        return 4;
    }
    /** INC */
    private final int opee() {
        loadAbsolute();
        inc();
        
        return 6;
    }
    /** Illegal command */
    private final int opef() { return 0; }

    /** BEQ */
    private final int opf0() {
        byte data = readProgramCounter();
        if((processorStatus & ZERO_FLAG) == ZERO_FLAG) {
            programCounter += data;
        }
        
        return 2;
    }
    /** SBC */
    private final int opf1() {
        loadIndirectY();
        sbc();
        
        return 5;
    }
    /** JAM Illegal command */
    private final int opf2() { return 0; }
    /** Illegal command */
    private final int opf3() { return 0; }
    /** NOP Illegal command */
    private final int opf4() {
        return 4;
    }
    /** SBC */
    private final int opf5() {
        loadZeroPageX();
        sbc();
        
        return 4;
    }
    /** INC */
    private final int opf6() {
        loadZeroPageX();
        inc();
        
        return 6;
    }
    /** Illegal command */
    private final int opf7() { return 0; }
    /** SED */
    private final int opf8() {
        setDecimal();
        
        return 2;
    }
    /** SBC */
    private final int opf9() {
        loadAbsoluteX();
        sbc();
        
        return 4;
    }
    /** NOP Illegal command */
    private final int opfa() {
        return 2;
    }
    /** Illegal command */
    private final int opfb() { return 0; }
    /** NOP Illegal command */
    private final int opfc() {
        return 4;
    }
    /** SBC */
    private final int opfd() {
        loadAbsoluteX();
        sbc();
        
        return 4;
    }
    /** INC */
    private final int opfe() {
        loadAbsoluteX();
        inc();
        
        return 7;
    }
    /** Illegal command */
    private final int opff() { return 0; }

    private final int jam() {
        programCounter--;
        return 1;
    }
    
    /** ADC *///fix for overflow
    private final void adc() {
        int temp = accumulator;
        temp += memory.read(addressBus);
        temp += (processorStatus & 0x01);
        byte flags = 0;
        accumulator = (byte)(temp & 0xff);
        if((temp & 0x0100) == 0x0100) {
            setCarry();
        }
        else {
            clearCarry();
        }
        checkZeroNegative(accumulator);
    }
    /** AND */
    private final void and() {
        int temp = accumulator;
        temp &= memory.read(addressBus);
        accumulator = (byte)(temp & 0xff);
        checkZeroNegative(accumulator);
    }
    
    private final void asl() {
        byte data = memory.read(addressBus);
        data = performAsl(data);
        memory.write(addressBus, data);
    }
    private final byte performAsl(byte data) {
        if((data & 0x80) == 0x80) {
            setCarry();
        }
        else {
            clearCarry();
        }
        data = (byte)(data << 1);
        checkZeroNegative(data);
        
        return data;
    }
    private final void bit() {
        byte data = memory.read(addressBus);
        byte test = (byte)(data | accumulator);
        checkZeroNegative(test);
        if((test & 0x40) == 0x40) {
            setOverflow();
        }
        else {
            clearOverflow();
        }
    }
    private final void cmp() {
        byte data = memory.read(addressBus);
        performCompare(accumulator, data);
    }
    private final void cpx() {
        byte data = memory.read(addressBus);
        performCompare(xRegister, data);
    }
    private final void cpy() {
        byte data = memory.read(addressBus);
        performCompare(yRegister, data);
    }
    private final void dec() {
        byte data = memory.read(addressBus);
        data = (byte)((data & 0xff) - 1);
        memory.write(addressBus, data);
        checkZeroNegative(data);
    }
    private final void eor() {
        byte data = memory.read(addressBus);
        accumulator = (byte)((accumulator & 0xff) ^ (data & 0xff));
        checkZeroNegative(accumulator);
    }
    private final void inc() {
        byte data = memory.read(addressBus);
        data = (byte)((data & 0xff) + 1);
        memory.write(addressBus, data);
        checkZeroNegative(data);
    }
    private final void lda() {
        accumulator = memory.read(addressBus);
        checkZeroNegative(accumulator);
    }
    private final void ldx() {
        xRegister = memory.read(addressBus);
        checkZeroNegative(xRegister);
    }
    private final void ldy() {
        yRegister = memory.read(addressBus);
        checkZeroNegative(yRegister);
    }
    private final void lsr() {
        byte data = memory.read(addressBus);
        data = performLsr(data);
        memory.write(addressBus, data);
    }
    private final void ora() {
        accumulator = (byte)((accumulator & 0xff) | (memory.read(addressBus) & 0xff));
        checkZeroNegative(accumulator);
    }
    private final void rol() {
        byte data = memory.read(addressBus);
        data = performRol(data);
        memory.write(addressBus, data);
    }
    private final void ror() {
        byte data = memory.read(addressBus);
        data = performRor(data);
        memory.write(addressBus, data);
    }
    private final void sbc() {
        byte data = memory.read(addressBus);
        int result = (accumulator & 0xff) - (data & 0xff) - (isCarry() ? 0 : 1);
        accumulator = (byte)(result & 0xff);
        byte setFlags = 0;
        byte clearFlags = 0;
        if(result >= 0) {
            setFlags |= CARRY_FLAG;
        }
        else {
            clearFlags |= CARRY_FLAG;
        }
        if((((accumulator & 0xff) ^ result) & 0x80) != 0 && (((accumulator & 0xff) ^ (data & 0xff)) & 0x80) != 0) {
            setFlags |= OVERFLOW_FLAG;
        }
        else {
            clearFlags |= OVERFLOW_FLAG;
        }
        setFlag(setFlags);
        clearFlag(clearFlags);
        checkZeroNegative(data);
    }
    private final void sta() {
        memory.write(addressBus, accumulator);
    }
    private final void stx() {
        memory.write(addressBus, xRegister);
    }
    private final void sty() {
        memory.write(addressBus, yRegister);
    }
    private final byte performRol(byte data) {
        boolean carrySet = ((data & 0x80) == 0x80);
        data = (byte)((data & 0xff) << 1);
        if(isCarry()) {
            data |= 0x01;
        }
        if(carrySet) {
            setCarry();
        }
        else {
            clearCarry();
        }
        checkZeroNegative(data);
        
        return data;
    }
    private final byte performRor(byte data) {
        boolean carrySet = ((data & 0x01) == 0x01);
        data = (byte)((data & 0xff) >>> 1);
        if(isCarry()) {
            data |= 0x80;
        }
        if(carrySet) {
            setCarry();
        }
        else {
            clearCarry();
        }
        checkZeroNegative(data);
        
        return data;
    }
    private final void performCompare(byte register, byte data) {
        byte setFlags = 0;
        byte clearFlags = 0;
        if((register & 0xff) < (data & 0xff)) {
            setFlags |= NEGATIVE_FLAG;
        }
        else {
            clearFlags |= NEGATIVE_FLAG;
        }
        if(register == data) {
            setFlags |= ZERO_FLAG;
        }
        else {
            clearFlags |= ZERO_FLAG;
        }
        setFlag(setFlags);
        clearFlag(clearFlags);
    }
    private byte performLsr(byte data) {
        if((data & 0x01) == 0x01) {
            setCarry();
        }
        else {
            clearCarry();
        }
        data = (byte)(((data & 0xff) >>> 1) & 0xff);
        checkZeroNegative(data);
        
        return data;
    }
    private final void checkZeroNegative(byte data) {
        byte setFlags = 0;
        byte clearFlags = 0;
        if((data & 0x80) == 0x80) {
            setFlags |= NEGATIVE_FLAG;
        }
        else {
            clearFlags |= NEGATIVE_FLAG;
        }
        if(data == 0x00) {
            setFlags |= ZERO_FLAG;
        }
        else {
            clearFlags |= ZERO_FLAG;
        }
        setFlag(setFlags);
        clearFlag(clearFlags);
    }
    
    private final void push(byte data) {
        memory.write(0x0100 + (stackPointer & 0xff), data);
        stackPointer = (byte)((stackPointer & 0xff) - 1);
    }

    private final byte pop() {
        stackPointer = (byte)((stackPointer & 0xff) + 1);
        byte data = memory.read(0x0100 + (stackPointer & 0xff));
        return data;
    }
    private final void setNegative() {
        setFlag(NEGATIVE_FLAG);
    }
    private final void clearNegative() {
        clearFlag(NEGATIVE_FLAG);
    }
    private final void setOverflow() {
        setFlag(OVERFLOW_FLAG);
    }
    private final void clearOverflow() {
        clearFlag(OVERFLOW_FLAG);
    }
    private final void setBreak() {
        setFlag(BREAK_FLAG);
    }
    private final void clearBreak() {
        clearFlag(BREAK_FLAG);
    }
    private final void setDecimal() {
        setFlag(DECIMAL_FLAG);
    }
    private final void clearDecimal() {
        clearFlag(DECIMAL_FLAG);
    }
    private final void setInterruptDisable() {
        setFlag(INTERRUPT_DISABLE_FLAG);
    }
    private final void clearInterruptDisable() {
        clearFlag(INTERRUPT_DISABLE_FLAG);
    }
    private final void setZero() {
        setFlag(ZERO_FLAG);
    }
    private final void clearZero() {
        clearFlag(ZERO_FLAG);
    }
    private final void setCarry() {
        setFlag(CARRY_FLAG);
    }
    private final void clearCarry() {
        clearFlag(CARRY_FLAG);
    }
    
    private final void setFlag(byte flag) {
        processorStatus |= flag;
    }
    
    private final void clearFlag(byte flag) {
        processorStatus &= (~flag);
    }

    private final boolean isNegative() {
        return ((processorStatus & NEGATIVE_FLAG) == NEGATIVE_FLAG);
    }

    private final boolean isOverflow() {
        return ((processorStatus & OVERFLOW_FLAG) == OVERFLOW_FLAG);
    }

    private final boolean isBreak() {
        return ((processorStatus & BREAK_FLAG) == BREAK_FLAG);
    }

    private final boolean isDecimal() {
        return ((processorStatus & DECIMAL_FLAG) == DECIMAL_FLAG);
    }

    private final boolean isInterruptDisable() {
        return ((processorStatus & INTERRUPT_DISABLE_FLAG) == INTERRUPT_DISABLE_FLAG);
    }

    private final boolean isZero() {
        return ((processorStatus & ZERO_FLAG) == ZERO_FLAG);
    }

    private final boolean isCarry() {
        return ((processorStatus & CARRY_FLAG) == CARRY_FLAG);
    }
    private final byte readProgramCounter() {
        byte data = memory.read(programCounter);
        programCounter++;
        
        return data;
    }
    private final void loadImmediate() {
        addressBus = programCounter;
        programCounter++;
    }
    private final void loadZeroPage() {
        addressBus = memory.read(programCounter);
        programCounter++;
    }
    private final void loadZeroPageX() {
        byte data = memory.read(programCounter);
        data = (byte)((data & 0xff) + (xRegister & 0xff));
        addressBus = (data & 0xff);
        programCounter++;
    }
    private final void loadZeroPageY() {
        byte data = memory.read(programCounter);
        data = (byte)((data & 0xff) + (yRegister & 0xff));
        addressBus = (data & 0xff);
        programCounter++;
    }
    private final void loadAbsolute() {
        byte data = memory.read(programCounter);
        programCounter++;
        addressBus = (data & 0xff);
        data = memory.read(programCounter);
        programCounter++;
        addressBus |= ((data & 0xff) << 8);
    }
    private final void loadAbsoluteX() {
        byte data = memory.read(programCounter);
        programCounter++;
        addressBus = (data & 0xff);
        data = memory.read(programCounter);
        programCounter++;
        addressBus |= ((data & 0xff) << 8);
        addressBus += xRegister;
    }
    private final void loadAbsoluteY() {
        byte data = memory.read(programCounter);
        programCounter++;
        addressBus = (data & 0xff);
        data = memory.read(programCounter);
        programCounter++;
        addressBus |= ((data & 0xff) << 8);
        addressBus += yRegister;
    }
    private final void loadAbsoluteIndirect() {
        byte data1 = memory.read(programCounter);
        programCounter++;
        byte data2 = memory.read(programCounter);
        programCounter++;
        addressBus = (data1 & 0xff) | ((data2 & 0xff) << 8); 

        data1 = memory.read(addressBus);
        addressBus++;
        data2 = memory.read(addressBus);
        addressBus = (data1 & 0xff) | ((data2 & 0xff) << 8); 
    }
    private final void loadIndirectX() {
        byte data = memory.read(programCounter);
        programCounter++;
        data = (byte)((data & 0xff) + (xRegister & 0xff));
        addressBus = (memory.read(data & 0xff) & 0xff) | ((memory.read((data & 0xff) + 1) & 0xff) << 8);
    }
    private final void loadIndirectY() {
        byte data = memory.read(programCounter);
        programCounter++;
        addressBus = ((memory.read(data & 0xff) & 0xff) | ((memory.read((data & 0xff) + 1) & 0xff) << 8)) + (yRegister & 0xff);
    }

    /**
     * @return the programCounter
     */
    public final int getProgramCounter() {
        return programCounter;
    }
    /**
     * @param programCounter the programCounter to set
     */
    public final void setProgramCounter(int programCounter) {
        this.programCounter = programCounter;
    }
    /**
     * @return the stackPointer
     */
    public byte getStackPointer() {
        return stackPointer;
    }
    /**
     * @param stackPointer the stackPointer to set
     */
    public void setStackPointer(byte stackPointer) {
        this.stackPointer = stackPointer;
    }
    /**
     * @return the xRegister
     */
    public byte getxRegister() {
        return xRegister;
    }
    /**
     * @param xRegister the xRegister to set
     */
    public void setxRegister(byte xRegister) {
        this.xRegister = xRegister;
    }
    /**
     * @return the yRegister
     */
    public byte getyRegister() {
        return yRegister;
    }
    /**
     * @param yRegister the yRegister to set
     */
    public void setyRegister(byte yRegister) {
        this.yRegister = yRegister;
    }
    /**
     * @return the accumulator
     */
    public byte getAccumulator() {
        return accumulator;
    }
    /**
     * @param accumulator the accumulator to set
     */
    public void setAccumulator(byte accumulator) {
        this.accumulator = accumulator;
    }
    /**
     * @return the statusRegister
     */
    public byte getProcessorStatus() {
        return processorStatus;
    }
    /**
     * @param statusRegister the statusRegister to set
     */
    public void setProcessorStatus(byte statusRegister) {
        this.processorStatus = statusRegister;
    }
    /**
     * @return the addressBus
     */
    public int getAddressBus() {
        return addressBus;
    }
    /**
     * @param addressBus the addressBus to set
     */
    public void setAddressBus(int addressBus) {
        this.addressBus = addressBus;
    }

    /**
     * For testing purposes only.
     * 
     * @param code
     * @return Cycle count
     */
    public int callMethod(String name) throws Exception {
        Method method = getClass().getDeclaredMethod(name, null);
        Object result = method.invoke(this, null);
        
        return ((Integer)result).intValue();
    }
    
    public static void main(String... args) throws Exception {
        System.out.println("Start:" + new Date());
        
        Cpu cpu = new Cpu();
        System.out.println(cpu.callMethod("next"));
        System.out.println(cpu.callMethod("next"));

        System.out.println("  End:" + new Date());
    }
}
