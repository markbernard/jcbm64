/**
 * 
 */
package test.io.github.markbernard.jcbm64;

import io.github.markbernard.jcbm64.Cpu;
import io.github.markbernard.jcbm64.Memory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Mark Bernard
 *
 */
public class CpuTest {
    @Before
    public void before() {
        Memory.reset();
    }
    @Test public void op00() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        Memory.instance.write(0xc000, (byte)0);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 7", 7, result);
        Assert.assertEquals("Program counter should equal 0xff48", 0xff48, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
        Assert.assertEquals("Stack pointer should equal 0xfd", 0xfd, (cpu.getStackPointer() & 0xff));
    }
    @Test public void op01() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setAccumulator((byte)0xaa);
        cpu.setxRegister((byte)0);
        Memory.instance.write(0xc000, (byte)0x01);
        Memory.instance.write(0xc001, (byte)0x32);
        Memory.instance.write(0x0008, (byte)0x55);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 6", 6, result);
        Assert.assertEquals("Program counter should equal 0xc002", 0xc002, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
        Assert.assertEquals("Accumulator should equal 0xff", 0xff, (cpu.getAccumulator() & 0xff));
    }
    @Test public void op02() throws Exception {}
    @Test public void op03() throws Exception {}
    @Test public void op04() throws Exception {
        testNop((byte)0x04, 3);
    }
    @Test public void op05() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setAccumulator((byte)0xaa);
        Memory.instance.write(0xc000, (byte)0x05);
        Memory.instance.write(0xc001, (byte)0x08);
        Memory.instance.write(0x0008, (byte)0x55);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 3", 3, result);
        Assert.assertEquals("Program counter should equal 0xc002", 0xc002, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
        Assert.assertEquals("Accumulator should equal 0xff", 0xff, (cpu.getAccumulator() & 0xff));
    }
    @Test public void op06() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        Memory.instance.write(0xc000, (byte)0x06);
        Memory.instance.write(0xc001, (byte)0x32);
        Memory.instance.write(0x0032, (byte)0x80);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 5", 5, result);
        Assert.assertEquals("Program counter should equal 0xc002", 0xc002, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0xb4", 0x37, (cpu.getProcessorStatus() & 0xff));
        Assert.assertEquals("Memory should equal 0x00", 0x00, (Memory.instance.read(0x0032) & 0xff));
    }
    @Test public void op07() throws Exception {}
    @Test public void op08() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        Memory.instance.write(0xc000, (byte)0x08);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 3", 3, result);
        Assert.assertEquals("Program counter should equal 0xc001", 0xc001, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
        Assert.assertEquals("Stack pointer should equal 0xfe", 0xfe, (cpu.getStackPointer() & 0xff));
        Assert.assertEquals("Memory should equal 0xb4", 0xb4, (Memory.instance.read(0x01ff) & 0xff));
    }
    @Test public void op09() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setAccumulator((byte)0xaa);
        Memory.instance.write(0xc000, (byte)0x09);
        Memory.instance.write(0xc001, (byte)0x55);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 2", 2, result);
        Assert.assertEquals("Program counter should equal 0xc002", 0xc002, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
        Assert.assertEquals("Accumulator should equal 0xff", 0xff, (cpu.getAccumulator() & 0xff));
    }
    @Test public void op0a() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setAccumulator((byte)0x80);
        Memory.instance.write(0xc000, (byte)0x0a);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 2", 2, result);
        Assert.assertEquals("Program counter should equal 0xc001", 0xc001, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0xb4", 0x37, (cpu.getProcessorStatus() & 0xff));
        Assert.assertEquals("Accumulator should equal 0x00", 0x00, (cpu.getAccumulator() & 0xff));
    }
    @Test public void op0b() throws Exception {}
    @Test public void op0c() throws Exception {
        testNop((byte)0x0c, 4);
    }
    @Test public void op0d() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setAccumulator((byte)0xaa);
        Memory.instance.write(0xc000, (byte)0x0d);
        Memory.instance.write(0xc001, (byte)0x03);
        Memory.instance.write(0xc002, (byte)0xc0);
        Memory.instance.write(0xc003, (byte)0x55);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 4", 4, result);
        Assert.assertEquals("Program counter should equal 0xc003", 0xc003, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
        Assert.assertEquals("Accumulator should equal 0xff", 0xff, (cpu.getAccumulator() & 0xff));
    }
    @Test public void op0e() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        Memory.instance.write(0xc000, (byte)0x0e);
        Memory.instance.write(0xc001, (byte)0x03);
        Memory.instance.write(0xc002, (byte)0xc0);
        Memory.instance.write(0xc003, (byte)0x80);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 5", 5, result);
        Assert.assertEquals("Program counter should equal 0xc003", 0xc003, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0xb4", 0x37, (cpu.getProcessorStatus() & 0xff));
        Assert.assertEquals("Memory should equal 0x00", 0x00, (Memory.instance.read(0xc003) & 0xff));
    }
    @Test public void op0f() throws Exception {}

    @Test public void op10() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setProcessorStatus((byte)((cpu.getProcessorStatus() & 0xff) & ((~Cpu.NEGATIVE_FLAG) & 0xff)));
        Memory.instance.write(0xc000, (byte)0xd0);
        Memory.instance.write(0xc001, (byte)0x10);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 2", 2, result);
        Assert.assertEquals("Program counter should equal 0xc012", 0xc012, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0x34", 0x34, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void op11() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setAccumulator((byte)0xaa);
        cpu.setyRegister((byte)0);
        Memory.instance.write(0xc000, (byte)0x11);
        Memory.instance.write(0xc001, (byte)0x32);
        Memory.instance.write(0x0032, (byte)0x03);
        Memory.instance.write(0x0033, (byte)0xc0);
        Memory.instance.write(0xc003, (byte)0x55);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 5", 5, result);
        Assert.assertEquals("Program counter should equal 0xc002", 0xc002, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
        Assert.assertEquals("Accumulator should equal 0xff", 0xff, (cpu.getAccumulator() & 0xff));
    }
    @Test public void op12() throws Exception {}
    @Test public void op13() throws Exception {}
    @Test public void op14() throws Exception {
        testNop((byte)0x14, 4);
    }
    @Test public void op15() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setAccumulator((byte)0xaa);
        cpu.setxRegister((byte)0x01);
        Memory.instance.write(0xc000, (byte)0x15);
        Memory.instance.write(0xc001, (byte)0x31);
        Memory.instance.write(0x0032, (byte)0x55);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 4", 4, result);
        Assert.assertEquals("Program counter should equal 0xc002", 0xc002, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
        Assert.assertEquals("Accumulator should equal 0xff", 0xff, (cpu.getAccumulator() & 0xff));
    }
    @Test public void op16() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setxRegister((byte)0x00);
        Memory.instance.write(0xc000, (byte)0x16);
        Memory.instance.write(0xc001, (byte)0x32);
        Memory.instance.write(0x0032, (byte)0x80);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 6", 6, result);
        Assert.assertEquals("Program counter should equal 0xc002", 0xc002, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0xb4", 0x37, (cpu.getProcessorStatus() & 0xff));
        Assert.assertEquals("Memory should equal 0x00", 0x00, (Memory.instance.read(0x0032) & 0xff));
    }
    @Test public void op17() throws Exception {}
    @Test public void op18() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setProcessorStatus((byte)((cpu.getProcessorStatus() & 0xff) | Cpu.CARRY_FLAG));
        Assert.assertEquals("Status register should equal 0xb5", 0xb5, (cpu.getProcessorStatus() & 0xff));
        Memory.instance.write(0xc000, (byte)0x18);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 2", 2, result);
        Assert.assertEquals("Program counter should equal 0xc001", 0xc001, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void op19() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setAccumulator((byte)0xaa);
        cpu.setyRegister((byte)0x01);
        Memory.instance.write(0xc000, (byte)0x19);
        Memory.instance.write(0xc001, (byte)0x02);
        Memory.instance.write(0xc002, (byte)0xc0);
        Memory.instance.write(0xc003, (byte)0x55);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 4", 4, result);
        Assert.assertEquals("Program counter should equal 0xc003", 0xc003, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
        Assert.assertEquals("Accumulator should equal 0xff", 0xff, (cpu.getAccumulator() & 0xff));
    }
    @Test public void op1a() throws Exception {
        testNop((byte)0x1a, 2);
    }
    @Test public void op1b() throws Exception {}
    @Test public void op1c() throws Exception {
        testNop((byte)0x1c, 4);
    }
    @Test public void op1d() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setAccumulator((byte)0xaa);
        cpu.setxRegister((byte)0x01);
        Memory.instance.write(0xc000, (byte)0x1d);
        Memory.instance.write(0xc001, (byte)0x02);
        Memory.instance.write(0xc002, (byte)0xc0);
        Memory.instance.write(0xc003, (byte)0x55);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 4", 4, result);
        Assert.assertEquals("Program counter should equal 0xc003", 0xc003, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
        Assert.assertEquals("Accumulator should equal 0xff", 0xff, (cpu.getAccumulator() & 0xff));
    }
    @Test public void op1e() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setxRegister((byte)0x00);
        Memory.instance.write(0xc000, (byte)0x1e);
        Memory.instance.write(0xc001, (byte)0x03);
        Memory.instance.write(0xc002, (byte)0xc0);
        Memory.instance.write(0xc003, (byte)0x80);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 7", 7, result);
        Assert.assertEquals("Program counter should equal 0xc003", 0xc003, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0xb4", 0x37, (cpu.getProcessorStatus() & 0xff));
        Assert.assertEquals("Memory should equal 0x00", 0x00, (Memory.instance.read(0xc003) & 0xff));
    }
    @Test public void op1f() throws Exception {}

    @Test public void op20() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        Memory.instance.write(0xc000, (byte)0x20);
        Memory.instance.write(0xc001, (byte)0x10);
        Memory.instance.write(0xc002, (byte)0xc0);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 6", 6, result);
        Assert.assertEquals("Program counter should equal 0xc002", 0xc010, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void op21() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setAccumulator((byte)0xaa);
        cpu.setxRegister((byte)0x00);
        cpu.setProcessorStatus((byte)((cpu.getProcessorStatus() & 0xff) | Cpu.CARRY_FLAG));
        Memory.instance.write(0xc000, (byte)0x21);
        Memory.instance.write(0xc001, (byte)0x32);
        Memory.instance.write(0x0032, (byte)0x03);
        Memory.instance.write(0x0033, (byte)0xc0);
        Memory.instance.write(0xc003, (byte)0x33);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 6", 6, result);
        Assert.assertEquals("Program counter should equal 0xc002", 0xc002, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0x35", 0x35, (cpu.getProcessorStatus() & 0xff));
        Assert.assertEquals("Accumulator should equal 0x22", 0x22, (cpu.getAccumulator() & 0xff));
    }
    @Test public void op22() throws Exception {}
    @Test public void op23() throws Exception {}
    @Test public void op24() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setAccumulator((byte)0xff);
        Memory.instance.write(0xc000, (byte)0x24);
        Memory.instance.write(0xc001, (byte)0x32);
        Memory.instance.write(0x0032, (byte)0xff);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 3", 3, result);
        Assert.assertEquals("Program counter should equal 0xc012", 0xc002, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0xf4", 0xf4, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void op25() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setAccumulator((byte)0xaa);
        cpu.setProcessorStatus((byte)((cpu.getProcessorStatus() & 0xff) | Cpu.CARRY_FLAG));
        Memory.instance.write(0xc000, (byte)0x25);
        Memory.instance.write(0xc001, (byte)0x32);
        Memory.instance.write(0x0032, (byte)0x33);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 3", 3, result);
        Assert.assertEquals("Program counter should equal 0xc002", 0xc002, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0x35", 0x35, (cpu.getProcessorStatus() & 0xff));
        Assert.assertEquals("Accumulator should equal 0x22", 0x22, (cpu.getAccumulator() & 0xff));
    }
    @Test public void op26() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        Memory.instance.write(0x0032, (byte)0xf0);
        cpu.setProcessorStatus((byte)((cpu.getProcessorStatus() & 0xff) | Cpu.CARRY_FLAG));
        Memory.instance.write(0xc000, (byte)0x26);
        Memory.instance.write(0xc001, (byte)0x32);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 5", 5, result);
        Assert.assertEquals("Program counter should equal 0xc002", 0xc002, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0xb5", 0xb5, (cpu.getProcessorStatus() & 0xff));
        Assert.assertEquals("Memory should equal 0xe1", 0xe1, (Memory.instance.read(0x0032) & 0xff));
    }
    @Test public void op27() throws Exception {}
    @Test public void op28() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setProcessorStatus((byte)0xff);
        cpu.setStackPointer((byte)0xfe);
        Memory.instance.write(0x01ff, (byte)0xaa);
        Memory.instance.write(0xc000, (byte)0x28);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 4", 4, result);
        Assert.assertEquals("Program counter should equal 0xc001", 0xc001, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0xaa", 0xaa, (cpu.getProcessorStatus() & 0xff));
        Assert.assertEquals("Stack pointer should equal 0xff", 0xff, (cpu.getStackPointer() & 0xff));
    }
    @Test public void op29() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setAccumulator((byte)0xaa);
        cpu.setProcessorStatus((byte)((cpu.getProcessorStatus() & 0xff) | Cpu.CARRY_FLAG));
        Memory.instance.write(0xc000, (byte)0x29);
        Memory.instance.write(0xc001, (byte)0x33);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 2", 2, result);
        Assert.assertEquals("Program counter should equal 0xc002", 0xc002, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0x35", 0x35, (cpu.getProcessorStatus() & 0xff));
        Assert.assertEquals("Accumulator should equal 0x22", 0x22, (cpu.getAccumulator() & 0xff));
    }
    @Test public void op2a() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setAccumulator((byte)0xf0);
        cpu.setProcessorStatus((byte)((cpu.getProcessorStatus() & 0xff) | Cpu.CARRY_FLAG));
        Memory.instance.write(0xc000, (byte)0x2a);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 2", 2, result);
        Assert.assertEquals("Program counter should equal 0xc001", 0xc001, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0xb5", 0xb5, (cpu.getProcessorStatus() & 0xff));
        Assert.assertEquals("Accumulator should equal 0xe1", 0xe1, (cpu.getAccumulator() & 0xff));
    }
    @Test public void op2b() throws Exception {}
    @Test public void op2c() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setAccumulator((byte)0xff);
        Memory.instance.write(0xc000, (byte)0x2c);
        Memory.instance.write(0xc001, (byte)0x03);
        Memory.instance.write(0xc002, (byte)0xc0);
        Memory.instance.write(0xc003, (byte)0xff);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 4", 4, result);
        Assert.assertEquals("Program counter should equal 0xc012", 0xc002, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0xf4", 0xf4, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void op2d() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setAccumulator((byte)0xaa);
        cpu.setProcessorStatus((byte)((cpu.getProcessorStatus() & 0xff) | Cpu.CARRY_FLAG));
        Memory.instance.write(0xc000, (byte)0x2d);
        Memory.instance.write(0xc001, (byte)0x03);
        Memory.instance.write(0xc002, (byte)0xc0);
        Memory.instance.write(0xc003, (byte)0x33);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 4", 4, result);
        Assert.assertEquals("Program counter should equal 0xc003", 0xc003, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0x35", 0x35, (cpu.getProcessorStatus() & 0xff));
        Assert.assertEquals("Accumulator should equal 0x22", 0x22, (cpu.getAccumulator() & 0xff));
    }
    @Test public void op2e() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        Memory.instance.write(0xc003, (byte)0xf0);
        cpu.setProcessorStatus((byte)((cpu.getProcessorStatus() & 0xff) | Cpu.CARRY_FLAG));
        Memory.instance.write(0xc000, (byte)0x2e);
        Memory.instance.write(0xc001, (byte)0x03);
        Memory.instance.write(0xc002, (byte)0xc0);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 6", 6, result);
        Assert.assertEquals("Program counter should equal 0xc003", 0xc003, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0xb5", 0xb5, (cpu.getProcessorStatus() & 0xff));
        Assert.assertEquals("Memory should equal 0xe1", 0xe1, (Memory.instance.read(0xc003) & 0xff));
    }
    @Test public void op2f() throws Exception {}

    @Test public void op30() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setProcessorStatus((byte)((cpu.getProcessorStatus() & 0xff) | Cpu.NEGATIVE_FLAG));
        Memory.instance.write(0xc000, (byte)0x30);
        Memory.instance.write(0xc001, (byte)0x10);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 2", 2, result);
        Assert.assertEquals("Program counter should equal 0xc012", 0xc012, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void op31() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setAccumulator((byte)0xaa);
        cpu.setyRegister((byte)0x00);
        cpu.setProcessorStatus((byte)((cpu.getProcessorStatus() & 0xff) | Cpu.CARRY_FLAG));
        Memory.instance.write(0xc000, (byte)0x31);
        Memory.instance.write(0xc001, (byte)0x32);
        Memory.instance.write(0x0032, (byte)0x03);
        Memory.instance.write(0x0033, (byte)0xc0);
        Memory.instance.write(0xc003, (byte)0x33);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 5", 5, result);
        Assert.assertEquals("Program counter should equal 0xc002", 0xc002, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0x35", 0x35, (cpu.getProcessorStatus() & 0xff));
        Assert.assertEquals("Accumulator should equal 0x22", 0x22, (cpu.getAccumulator() & 0xff));
    }
    @Test public void op32() throws Exception {}
    @Test public void op33() throws Exception {}
    @Test public void op34() throws Exception {
        testNop((byte)0x34, 4);
    }
    @Test public void op35() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setAccumulator((byte)0xaa);
        cpu.setxRegister((byte)0x00);
        cpu.setProcessorStatus((byte)((cpu.getProcessorStatus() & 0xff) | Cpu.CARRY_FLAG));
        Memory.instance.write(0xc000, (byte)0x35);
        Memory.instance.write(0xc001, (byte)0x32);
        Memory.instance.write(0x0032, (byte)0x33);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 4", 4, result);
        Assert.assertEquals("Program counter should equal 0xc002", 0xc002, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0x35", 0x35, (cpu.getProcessorStatus() & 0xff));
        Assert.assertEquals("Accumulator should equal 0x22", 0x22, (cpu.getAccumulator() & 0xff));
    }
    @Test public void op36() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        Memory.instance.write(0x0032, (byte)0xf0);
        cpu.setxRegister((byte)0x01);
        cpu.setProcessorStatus((byte)((cpu.getProcessorStatus() & 0xff) | Cpu.CARRY_FLAG));
        Memory.instance.write(0xc000, (byte)0x36);
        Memory.instance.write(0xc001, (byte)0x31);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 6", 6, result);
        Assert.assertEquals("Program counter should equal 0xc002", 0xc002, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0xb5", 0xb5, (cpu.getProcessorStatus() & 0xff));
        Assert.assertEquals("Memory should equal 0xe1", 0xe1, (Memory.instance.read(0x0032) & 0xff));
    }
    @Test public void op37() throws Exception {}
    @Test public void op38() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setProcessorStatus((byte)((cpu.getProcessorStatus() & 0xff) & ((~Cpu.CARRY_FLAG) & 0xff)));
        Memory.instance.write(0xc000, (byte)0x38);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 2", 2, result);
        Assert.assertEquals("Program counter should equal 0xc001", 0xc001, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0xb5", 0xb5, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void op39() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setAccumulator((byte)0xaa);
        cpu.setyRegister((byte)0x00);
        cpu.setProcessorStatus((byte)((cpu.getProcessorStatus() & 0xff) | Cpu.CARRY_FLAG));
        Memory.instance.write(0xc000, (byte)0x39);
        Memory.instance.write(0xc001, (byte)0x03);
        Memory.instance.write(0xc002, (byte)0xc0);
        Memory.instance.write(0xc003, (byte)0x33);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 4", 4, result);
        Assert.assertEquals("Program counter should equal 0xc003", 0xc003, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0x35", 0x35, (cpu.getProcessorStatus() & 0xff));
        Assert.assertEquals("Accumulator should equal 0x22", 0x22, (cpu.getAccumulator() & 0xff));
    }
    @Test public void op3a() throws Exception {
        testNop((byte)0x3a, 2);
    }
    @Test public void op3b() throws Exception {}
    @Test public void op3c() throws Exception {
        testNop((byte)0x3c, 4);
    }
    @Test public void op3d() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setAccumulator((byte)0xaa);
        cpu.setxRegister((byte)0x00);
        cpu.setProcessorStatus((byte)((cpu.getProcessorStatus() & 0xff) | Cpu.CARRY_FLAG));
        Memory.instance.write(0xc000, (byte)0x3d);
        Memory.instance.write(0xc001, (byte)0x03);
        Memory.instance.write(0xc002, (byte)0xc0);
        Memory.instance.write(0xc003, (byte)0x33);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 4", 4, result);
        Assert.assertEquals("Program counter should equal 0xc003", 0xc003, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0x35", 0x35, (cpu.getProcessorStatus() & 0xff));
        Assert.assertEquals("Accumulator should equal 0x22", 0x22, (cpu.getAccumulator() & 0xff));
    }
    @Test public void op3e() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setxRegister((byte)0x01);
        Memory.instance.write(0xc003, (byte)0xf0);
        cpu.setProcessorStatus((byte)((cpu.getProcessorStatus() & 0xff) | Cpu.CARRY_FLAG));
        Memory.instance.write(0xc000, (byte)0x3e);
        Memory.instance.write(0xc001, (byte)0x02);
        Memory.instance.write(0xc002, (byte)0xc0);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 7", 7, result);
        Assert.assertEquals("Program counter should equal 0xc003", 0xc003, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0xb5", 0xb5, (cpu.getProcessorStatus() & 0xff));
        Assert.assertEquals("Memory should equal 0xe1", 0xe1, (Memory.instance.read(0xc003) & 0xff));
    }
    @Test public void op3f() throws Exception {}

    @Test public void op40() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        Memory.instance.write(0x01ff, (byte)0xc0);
        Memory.instance.write(0x01fe, (byte)0x10);
        Memory.instance.write(0x01fd, (byte)0xaa);
        cpu.setStackPointer((byte)0xfc);
        cpu.setProcessorStatus((byte)((cpu.getProcessorStatus() & 0xff) | Cpu.CARRY_FLAG));
        Memory.instance.write(0xc000, (byte)0x40);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 6", 6, result);
        Assert.assertEquals("Program counter should equal 0xc010", 0xc010, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0xaa", 0xaa, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void op41() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setxRegister((byte)0x00);
        cpu.setAccumulator((byte)0xff);
        Memory.instance.write(0xc000, (byte)0x41);
        Memory.instance.write(0xc001, (byte)0x32);
        Memory.instance.write(0x0032, (byte)0x03);
        Memory.instance.write(0x0033, (byte)0xc0);
        Memory.instance.write(0xc003, (byte)0xaa);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 6", 6, result);
        Assert.assertEquals("Program counter should equal 0xc002", 0xc002, cpu.getProgramCounter());
        Assert.assertEquals("Accumulator should equal 0x55", 0x55, (cpu.getAccumulator() & 0xff));
        Assert.assertEquals("Status register should equal 0x34", 0x34, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void op42() throws Exception {}
    @Test public void op43() throws Exception {}
    @Test public void op44() throws Exception {
        testNop((byte)0x44, 3);
    }
    @Test public void op45() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setxRegister((byte)0x00);
        cpu.setAccumulator((byte)0xff);
        Memory.instance.write(0xc000, (byte)0x45);
        Memory.instance.write(0xc001, (byte)0x32);
        Memory.instance.write(0x0032, (byte)0xaa);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 3", 3, result);
        Assert.assertEquals("Program counter should equal 0xc002", 0xc002, cpu.getProgramCounter());
        Assert.assertEquals("Accumulator should equal 0x55", 0x55, (cpu.getAccumulator() & 0xff));
        Assert.assertEquals("Status register should equal 0x34", 0x34, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void op46() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        Memory.instance.write(0xc000, (byte)0x46);
        Memory.instance.write(0xc001, (byte)0x32);
        Memory.instance.write(0x0032, (byte)0xff);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 5", 5, result);
        Assert.assertEquals("Program counter should equal 0xc002", 0xc002, cpu.getProgramCounter());
        Assert.assertEquals("Memory should equal 0x7f", 0x7f, (Memory.instance.read(0x0032) & 0xff));
        Assert.assertEquals("Status register should equal 0x35", 0x35, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void op47() throws Exception {}
    @Test public void op48() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setAccumulator((byte)0xaa);
        Memory.instance.write(0xc000, (byte)0x48);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 3", 3, result);
        Assert.assertEquals("Program counter should equal 0xc001", 0xc001, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
        Assert.assertEquals("Stack pointer should equal 0xfe", 0xfe, (cpu.getStackPointer() & 0xff));
        Assert.assertEquals("Memory should equal 0xaa", 0xaa, (Memory.instance.read(0x01ff) & 0xff));
    }
    @Test public void op49() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setxRegister((byte)0x00);
        cpu.setAccumulator((byte)0xff);
        Memory.instance.write(0xc000, (byte)0x49);
        Memory.instance.write(0xc001, (byte)0xaa);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 2", 2, result);
        Assert.assertEquals("Program counter should equal 0xc002", 0xc002, cpu.getProgramCounter());
        Assert.assertEquals("Accumulator should equal 0x55", 0x55, (cpu.getAccumulator() & 0xff));
        Assert.assertEquals("Status register should equal 0x34", 0x34, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void op4a() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setAccumulator((byte)0xff);
        Memory.instance.write(0xc000, (byte)0x4a);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 2", 2, result);
        Assert.assertEquals("Program counter should equal 0xc001", 0xc001, cpu.getProgramCounter());
        Assert.assertEquals("Accumulator should equal 0x7f", 0x7f, (cpu.getAccumulator() & 0xff));
        Assert.assertEquals("Status register should equal 0x35", 0x35, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void op4b() throws Exception {}
    @Test public void op4c() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        Memory.instance.write(0xc000, (byte)0x4c);
        Memory.instance.write(0xc001, (byte)0x10);
        Memory.instance.write(0xc002, (byte)0xc0);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 3", 3, result);
        Assert.assertEquals("Program counter should equal 0xc010", 0xc010, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void op4d() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setxRegister((byte)0x00);
        cpu.setAccumulator((byte)0xff);
        Memory.instance.write(0xc000, (byte)0x4d);
        Memory.instance.write(0xc001, (byte)0x03);
        Memory.instance.write(0xc002, (byte)0xc0);
        Memory.instance.write(0xc003, (byte)0xaa);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 4", 4, result);
        Assert.assertEquals("Program counter should equal 0xc003", 0xc003, cpu.getProgramCounter());
        Assert.assertEquals("Accumulator should equal 0x55", 0x55, (cpu.getAccumulator() & 0xff));
        Assert.assertEquals("Status register should equal 0x34", 0x34, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void op4e() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        Memory.instance.write(0xc000, (byte)0x4e);
        Memory.instance.write(0xc001, (byte)0x03);
        Memory.instance.write(0xc002, (byte)0xc0);
        Memory.instance.write(0xc003, (byte)0xff);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 6", 6, result);
        Assert.assertEquals("Program counter should equal 0xc003", 0xc003, cpu.getProgramCounter());
        Assert.assertEquals("Memory should equal 0x7f", 0x7f, (Memory.instance.read(0xc003) & 0xff));
        Assert.assertEquals("Status register should equal 0x35", 0x35, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void op4f() throws Exception {}

    @Test public void op50() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setProcessorStatus((byte)((cpu.getProcessorStatus() & 0xff) & ((~Cpu.OVERFLOW_FLAG) & 0xff)));
        Memory.instance.write(0xc000, (byte)0x50);
        Memory.instance.write(0xc001, (byte)0x10);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 2", 2, result);
        Assert.assertEquals("Program counter should equal 0xc012", 0xc012, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void op51() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setxRegister((byte)0x00);
        cpu.setAccumulator((byte)0xff);
        Memory.instance.write(0xc000, (byte)0x51);
        Memory.instance.write(0xc001, (byte)0x32);
        Memory.instance.write(0x0032, (byte)0x03);
        Memory.instance.write(0x0033, (byte)0xc0);
        Memory.instance.write(0xc003, (byte)0xaa);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 5", 5, result);
        Assert.assertEquals("Program counter should equal 0xc002", 0xc002, cpu.getProgramCounter());
        Assert.assertEquals("Accumulator should equal 0x55", 0x55, (cpu.getAccumulator() & 0xff));
        Assert.assertEquals("Status register should equal 0x34", 0x34, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void op52() throws Exception {}
    @Test public void op53() throws Exception {}
    @Test public void op54() throws Exception {
        testNop((byte)0x54, 4);
    }
    @Test public void op55() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setxRegister((byte)0x00);
        cpu.setAccumulator((byte)0xff);
        Memory.instance.write(0xc000, (byte)0x55);
        Memory.instance.write(0xc001, (byte)0x32);
        Memory.instance.write(0x0032, (byte)0xaa);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 4", 4, result);
        Assert.assertEquals("Program counter should equal 0xc002", 0xc002, cpu.getProgramCounter());
        Assert.assertEquals("Accumulator should equal 0x55", 0x55, (cpu.getAccumulator() & 0xff));
        Assert.assertEquals("Status register should equal 0x34", 0x34, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void op56() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setxRegister((byte)0x01);
        Memory.instance.write(0xc000, (byte)0x56);
        Memory.instance.write(0xc001, (byte)0x31);
        Memory.instance.write(0x0032, (byte)0xff);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 6", 6, result);
        Assert.assertEquals("Program counter should equal 0xc002", 0xc002, cpu.getProgramCounter());
        Assert.assertEquals("Memory should equal 0x7f", 0x7f, (Memory.instance.read(0x0032) & 0xff));
        Assert.assertEquals("Status register should equal 0x35", 0x35, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void op57() throws Exception {}
    @Test public void op58() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setProcessorStatus((byte)((cpu.getProcessorStatus() & 0xff) | Cpu.INTERRUPT_DISABLE_FLAG));
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
        Memory.instance.write(0xc000, (byte)0x58);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 2", 2, result);
        Assert.assertEquals("Program counter should equal 0xc001", 0xc001, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0xb0", 0xb0, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void op59() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setyRegister((byte)0x00);
        cpu.setAccumulator((byte)0xff);
        Memory.instance.write(0xc000, (byte)0x59);
        Memory.instance.write(0xc001, (byte)0x03);
        Memory.instance.write(0xc002, (byte)0xc0);
        Memory.instance.write(0xc003, (byte)0xaa);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 4", 4, result);
        Assert.assertEquals("Program counter should equal 0xc003", 0xc003, cpu.getProgramCounter());
        Assert.assertEquals("Accumulator should equal 0x55", 0x55, (cpu.getAccumulator() & 0xff));
        Assert.assertEquals("Status register should equal 0x34", 0x34, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void op5a() throws Exception {
        testNop((byte)0x5a, 2);
    }
    @Test public void op5b() throws Exception {}
    @Test public void op5c() throws Exception {
        testNop((byte)0x5c, 4);
    }
    @Test public void op5d() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setxRegister((byte)0x00);
        cpu.setAccumulator((byte)0xff);
        Memory.instance.write(0xc000, (byte)0x59);
        Memory.instance.write(0xc001, (byte)0x03);
        Memory.instance.write(0xc002, (byte)0xc0);
        Memory.instance.write(0xc003, (byte)0xaa);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 4", 4, result);
        Assert.assertEquals("Program counter should equal 0xc003", 0xc003, cpu.getProgramCounter());
        Assert.assertEquals("Accumulator should equal 0x55", 0x55, (cpu.getAccumulator() & 0xff));
        Assert.assertEquals("Status register should equal 0x34", 0x34, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void op5e() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setxRegister((byte)0x01);
        Memory.instance.write(0xc000, (byte)0x5e);
        Memory.instance.write(0xc001, (byte)0x02);
        Memory.instance.write(0xc002, (byte)0xc0);
        Memory.instance.write(0xc003, (byte)0xff);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 7", 7, result);
        Assert.assertEquals("Program counter should equal 0xc003", 0xc003, cpu.getProgramCounter());
        Assert.assertEquals("Memory should equal 0x7f", 0x7f, (Memory.instance.read(0xc003) & 0xff));
        Assert.assertEquals("Status register should equal 0x35", 0x35, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void op5f() throws Exception {}

    @Test public void op60() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        Memory.instance.write(0x01ff, (byte)0xc0);
        Memory.instance.write(0x01fe, (byte)0x0f);
        cpu.setStackPointer((byte)0xfd);
        cpu.setProcessorStatus((byte)((cpu.getProcessorStatus() & 0xff) | Cpu.CARRY_FLAG));
        Memory.instance.write(0xc000, (byte)0x60);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 6", 6, result);
        Assert.assertEquals("Program counter should equal 0xc010", 0xc010, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0xb5", 0xb5, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void op61() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setAccumulator((byte)0x04);
        cpu.setxRegister((byte)0);
        cpu.setProcessorStatus((byte)((cpu.getProcessorStatus() & 0xff) | Cpu.CARRY_FLAG));
        Memory.instance.write(0xc000, (byte)0x61);
        Memory.instance.write(0xc001, (byte)0x32);
        Memory.instance.write(0x0032, (byte)0x03);
        Memory.instance.write(0x0033, (byte)0xc0);
        Memory.instance.write(0xc003, (byte)0x04);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 6", 6, result);
        Assert.assertEquals("Program counter should equal 0xc002", 0xc002, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0x34", 0x34, (cpu.getProcessorStatus() & 0xff));
        Assert.assertEquals("Accumulator should equal 0x09", 0x09, (cpu.getAccumulator() & 0xff));
    }
    @Test public void op62() throws Exception {}
    @Test public void op63() throws Exception {}
    @Test public void op64() throws Exception {
        testNop((byte)0x64, 3);
    }
    @Test public void op65() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setAccumulator((byte)0x04);
        cpu.setProcessorStatus((byte)((cpu.getProcessorStatus() & 0xff) | Cpu.CARRY_FLAG));
        Memory.instance.write(0xc000, (byte)0x65);
        Memory.instance.write(0xc001, (byte)0x32);
        Memory.instance.write(0x0032, (byte)0x04);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 3", 3, result);
        Assert.assertEquals("Program counter should equal 0xc002", 0xc002, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0x34", 0x34, (cpu.getProcessorStatus() & 0xff));
        Assert.assertEquals("Accumulator should equal 0x09", 0x09, (cpu.getAccumulator() & 0xff));
    }
    @Test public void op66() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        Memory.instance.write(0x0032, (byte)0xe1);
        cpu.setProcessorStatus((byte)((cpu.getProcessorStatus() & 0xff) | Cpu.CARRY_FLAG));
        Memory.instance.write(0xc000, (byte)0x66);
        Memory.instance.write(0xc001, (byte)0x32);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 5", 5, result);
        Assert.assertEquals("Program counter should equal 0xc002", 0xc002, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0xb5", 0xb5, (cpu.getProcessorStatus() & 0xff));
        Assert.assertEquals("Memory should equal 0xf0", 0xf0, (Memory.instance.read(0x0032) & 0xff));
    }
    @Test public void op67() throws Exception {}
    @Test public void op68() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        Memory.instance.write(0x1ff, (byte)0xaa);
        cpu.setStackPointer((byte)0xfe);
        Memory.instance.write(0xc000, (byte)0x68);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 4", 4, result);
        Assert.assertEquals("Program counter should equal 0xc001", 0xc001, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
        Assert.assertEquals("Stack pointer should equal 0xff", 0xff, (cpu.getStackPointer() & 0xff));
        Assert.assertEquals("Memory should equal 0xaa", 0xaa, (Memory.instance.read(0x01ff) & 0xff));
    }
    @Test public void op69() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setAccumulator((byte)0x04);
        cpu.setxRegister((byte)0);
        cpu.setProcessorStatus((byte)((cpu.getProcessorStatus() & 0xff) | Cpu.CARRY_FLAG));
        Memory.instance.write(0xc000, (byte)0x69);
        Memory.instance.write(0xc001, (byte)0x04);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 2", 2, result);
        Assert.assertEquals("Program counter should equal 0xc002", 0xc002, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0x34", 0x34, (cpu.getProcessorStatus() & 0xff));
        Assert.assertEquals("Accumulator should equal 0x09", 0x09, (cpu.getAccumulator() & 0xff));
    }
    @Test public void op6a() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setAccumulator((byte)0xe1);
        cpu.setProcessorStatus((byte)((cpu.getProcessorStatus() & 0xff) | Cpu.CARRY_FLAG));
        Memory.instance.write(0xc000, (byte)0x6a);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 2", 2, result);
        Assert.assertEquals("Program counter should equal 0xc001", 0xc001, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0xb5", 0xb5, (cpu.getProcessorStatus() & 0xff));
        Assert.assertEquals("Accumulator should equal 0xf0", 0xf0, (cpu.getAccumulator() & 0xff));
    }
    @Test public void op6b() throws Exception {}
    @Test public void op6c() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        Memory.instance.write(0xc000, (byte)0x6c);
        Memory.instance.write(0xc001, (byte)0x05);
        Memory.instance.write(0xc002, (byte)0xc0);
        Memory.instance.write(0xc005, (byte)0x10);
        Memory.instance.write(0xc006, (byte)0xc0);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 5", 5, result);
        Assert.assertEquals("Program counter should equal 0xc010", 0xc010, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void op6d() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setAccumulator((byte)0x04);
        cpu.setProcessorStatus((byte)((cpu.getProcessorStatus() & 0xff) | Cpu.CARRY_FLAG));
        Memory.instance.write(0xc000, (byte)0x6d);
        Memory.instance.write(0xc001, (byte)0x03);
        Memory.instance.write(0xc002, (byte)0xc0);
        Memory.instance.write(0xc003, (byte)0x04);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 4", 4, result);
        Assert.assertEquals("Program counter should equal 0xc003", 0xc003, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0x34", 0x34, (cpu.getProcessorStatus() & 0xff));
        Assert.assertEquals("Accumulator should equal 0x09", 0x09, (cpu.getAccumulator() & 0xff));
    }
    @Test public void op6e() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        Memory.instance.write(0xc003, (byte)0xe1);
        cpu.setProcessorStatus((byte)((cpu.getProcessorStatus() & 0xff) | Cpu.CARRY_FLAG));
        Memory.instance.write(0xc000, (byte)0x6e);
        Memory.instance.write(0xc001, (byte)0x03);
        Memory.instance.write(0xc002, (byte)0xc0);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 6", 6, result);
        Assert.assertEquals("Program counter should equal 0xc003", 0xc003, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0xb5", 0xb5, (cpu.getProcessorStatus() & 0xff));
        Assert.assertEquals("Memory should equal 0xf0", 0xf0, (Memory.instance.read(0xc003) & 0xff));
    }
    @Test public void op6f() throws Exception {}

    @Test public void op70() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setProcessorStatus((byte)((cpu.getProcessorStatus() & 0xff) | Cpu.OVERFLOW_FLAG));
        Memory.instance.write(0xc000, (byte)0x70);
        Memory.instance.write(0xc001, (byte)0x10);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 2", 2, result);
        Assert.assertEquals("Program counter should equal 0xc012", 0xc012, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0xf4", 0xf4, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void op71() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setAccumulator((byte)0x04);
        cpu.setyRegister((byte)0x00);
        cpu.setProcessorStatus((byte)((cpu.getProcessorStatus() & 0xff) | Cpu.CARRY_FLAG));
        Memory.instance.write(0xc000, (byte)0x71);
        Memory.instance.write(0xc001, (byte)0x32);
        Memory.instance.write(0x0032, (byte)0x03);
        Memory.instance.write(0x0033, (byte)0xc0);
        Memory.instance.write(0xc003, (byte)0x04);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 5", 5, result);
        Assert.assertEquals("Program counter should equal 0xc002", 0xc002, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0x34", 0x34, (cpu.getProcessorStatus() & 0xff));
        Assert.assertEquals("Accumulator should equal 0x09", 0x09, (cpu.getAccumulator() & 0xff));
    }
    @Test public void op72() throws Exception {}
    @Test public void op73() throws Exception {}
    @Test public void op74() throws Exception {
        testNop((byte)0x74, 4);
    }
    @Test public void op75() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setAccumulator((byte)0x04);
        cpu.setxRegister((byte)0x00);
        cpu.setProcessorStatus((byte)((cpu.getProcessorStatus() & 0xff) | Cpu.CARRY_FLAG));
        Memory.instance.write(0xc000, (byte)0x75);
        Memory.instance.write(0xc001, (byte)0x32);
        Memory.instance.write(0x0032, (byte)0x04);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 4", 4, result);
        Assert.assertEquals("Program counter should equal 0xc002", 0xc002, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0x34", 0x34, (cpu.getProcessorStatus() & 0xff));
        Assert.assertEquals("Accumulator should equal 0x09", 0x09, (cpu.getAccumulator() & 0xff));
    }
    @Test public void op76() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setxRegister((byte)0x01);
        Memory.instance.write(0x0032, (byte)0xe1);
        cpu.setProcessorStatus((byte)((cpu.getProcessorStatus() & 0xff) | Cpu.CARRY_FLAG));
        Memory.instance.write(0xc000, (byte)0x76);
        Memory.instance.write(0xc001, (byte)0x31);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 6", 6, result);
        Assert.assertEquals("Program counter should equal 0xc002", 0xc002, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0xb5", 0xb5, (cpu.getProcessorStatus() & 0xff));
        Assert.assertEquals("Memory should equal 0xf0", 0xf0, (Memory.instance.read(0x0032) & 0xff));
    }
    @Test public void op77() throws Exception {}
    @Test public void op78() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setProcessorStatus((byte)((cpu.getProcessorStatus() & 0xff) & ((~Cpu.INTERRUPT_DISABLE_FLAG) & 0xff)));
        Memory.instance.write(0xc000, (byte)0x78);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 2", 2, result);
        Assert.assertEquals("Program counter should equal 0xc001", 0xc001, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void op79() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setAccumulator((byte)0x04);
        cpu.setyRegister((byte)0x00);
        cpu.setProcessorStatus((byte)((cpu.getProcessorStatus() & 0xff) | Cpu.CARRY_FLAG));
        Memory.instance.write(0xc000, (byte)0x79);
        Memory.instance.write(0xc001, (byte)0x03);
        Memory.instance.write(0xc002, (byte)0xc0);
        Memory.instance.write(0xc003, (byte)0x04);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 4", 4, result);
        Assert.assertEquals("Program counter should equal 0xc003", 0xc003, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0x34", 0x34, (cpu.getProcessorStatus() & 0xff));
        Assert.assertEquals("Accumulator should equal 0x09", 0x09, (cpu.getAccumulator() & 0xff));
    }
    @Test public void op7a() throws Exception {
        testNop((byte)0x7a, 2);
    }
    @Test public void op7b() throws Exception {}
    @Test public void op7c() throws Exception {
        testNop((byte)0x7c, 4);
    }
    @Test public void op7d() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setAccumulator((byte)0x04);
        cpu.setxRegister((byte)0x00);
        cpu.setProcessorStatus((byte)((cpu.getProcessorStatus() & 0xff) | Cpu.CARRY_FLAG));
        Memory.instance.write(0xc000, (byte)0x7d);
        Memory.instance.write(0xc001, (byte)0x03);
        Memory.instance.write(0xc002, (byte)0xc0);
        Memory.instance.write(0xc003, (byte)0x04);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 4", 4, result);
        Assert.assertEquals("Program counter should equal 0xc003", 0xc003, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0x34", 0x34, (cpu.getProcessorStatus() & 0xff));
        Assert.assertEquals("Accumulator should equal 0x09", 0x09, (cpu.getAccumulator() & 0xff));
    }
    @Test public void op7e() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setxRegister((byte)0x01);
        Memory.instance.write(0xc003, (byte)0xe1);
        cpu.setProcessorStatus((byte)((cpu.getProcessorStatus() & 0xff) | Cpu.CARRY_FLAG));
        Memory.instance.write(0xc000, (byte)0x7e);
        Memory.instance.write(0xc001, (byte)0x02);
        Memory.instance.write(0xc002, (byte)0xc0);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 7", 7, result);
        Assert.assertEquals("Program counter should equal 0xc003", 0xc003, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0xb5", 0xb5, (cpu.getProcessorStatus() & 0xff));
        Assert.assertEquals("Memory should equal 0xf0", 0xf0, (Memory.instance.read(0xc003) & 0xff));
    }
    @Test public void op7f() throws Exception {}

    @Test public void op80() throws Exception {
        testNop((byte)0x80, 2);
    }
    @Test public void op81() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setAccumulator((byte)0xaa);
        cpu.setxRegister((byte)0x01);
        Memory.instance.write(0xc000, (byte)0x81);
        Memory.instance.write(0xc001, (byte)0x31);
        Memory.instance.write(0x0032, (byte)0x03);
        Memory.instance.write(0x0033, (byte)0xc0);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 6", 6, result);
        Assert.assertEquals("Program counter should equal 0xc002", 0xc002, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
        Assert.assertEquals("Memory should equal 0xaa", 0xaa, (Memory.instance.read(0xc003) & 0xff));
    }
    @Test public void op82() throws Exception {
        testNop((byte)0x82, 2);
    }
    @Test public void op83() throws Exception {}
    @Test public void op84() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setyRegister((byte)0xaa);
        Memory.instance.write(0xc000, (byte)0x84);
        Memory.instance.write(0xc001, (byte)0x32);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 3", 3, result);
        Assert.assertEquals("Program counter should equal 0xc002", 0xc002, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
        Assert.assertEquals("Memory should equal 0xaa", 0xaa, (Memory.instance.read(0x0032) & 0xff));
    }
    @Test public void op85() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setAccumulator((byte)0xaa);
        Memory.instance.write(0xc000, (byte)0x85);
        Memory.instance.write(0xc001, (byte)0x32);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 3", 3, result);
        Assert.assertEquals("Program counter should equal 0xc002", 0xc002, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
        Assert.assertEquals("Memory should equal 0xaa", 0xaa, (Memory.instance.read(0x0032) & 0xff));
    }
    @Test public void op86() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setxRegister((byte)0xaa);
        Memory.instance.write(0xc000, (byte)0x86);
        Memory.instance.write(0xc001, (byte)0x32);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 3", 3, result);
        Assert.assertEquals("Program counter should equal 0xc002", 0xc002, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
        Assert.assertEquals("Memory should equal 0xaa", 0xaa, (Memory.instance.read(0x0032) & 0xff));
    }
    @Test public void op87() throws Exception {}
    @Test public void op88() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setyRegister((byte)0xff);
        Memory.instance.write(0xc000, (byte)0x88);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 2", 2, result);
        Assert.assertEquals("Program counter should equal 0xc001", 0xc001, cpu.getProgramCounter());
        Assert.assertEquals("Y Register should equal 0xfe", 0xfe, (cpu.getyRegister() & 0xff));
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void op89() throws Exception {
        testNop((byte)0x89, 2);
    }
    @Test public void op8a() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setxRegister((byte)0xaa);
        Memory.instance.write(0xc000, (byte)0x8a);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 2", 2, result);
        Assert.assertEquals("Program counter should equal 0xc001", 0xc001, cpu.getProgramCounter());
        Assert.assertEquals("Accumulator should equal 0xaa", 0xaa, (cpu.getAccumulator() & 0xff));
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void op8b() throws Exception {}
    @Test public void op8c() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setyRegister((byte)0xaa);
        Memory.instance.write(0xc000, (byte)0x8c);
        Memory.instance.write(0xc001, (byte)0x03);
        Memory.instance.write(0xc002, (byte)0xc0);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 4", 4, result);
        Assert.assertEquals("Program counter should equal 0xc003", 0xc003, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
        Assert.assertEquals("Memory should equal 0xaa", 0xaa, (Memory.instance.read(0xc003) & 0xff));
    }
    @Test public void op8d() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setAccumulator((byte)0xaa);
        Memory.instance.write(0xc000, (byte)0x8d);
        Memory.instance.write(0xc001, (byte)0x03);
        Memory.instance.write(0xc002, (byte)0xc0);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 4", 4, result);
        Assert.assertEquals("Program counter should equal 0xc003", 0xc003, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
        Assert.assertEquals("Memory should equal 0xaa", 0xaa, (Memory.instance.read(0xc003) & 0xff));
    }
    @Test public void op8e() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setxRegister((byte)0xaa);
        Memory.instance.write(0xc000, (byte)0x8e);
        Memory.instance.write(0xc001, (byte)0x03);
        Memory.instance.write(0xc002, (byte)0xc0);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 4", 4, result);
        Assert.assertEquals("Program counter should equal 0xc003", 0xc003, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
        Assert.assertEquals("Memory should equal 0xaa", 0xaa, (Memory.instance.read(0xc003) & 0xff));
    }
    @Test public void op8f() throws Exception {}

    @Test public void op90() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setProcessorStatus((byte)((cpu.getProcessorStatus() & 0xff) & ((~Cpu.CARRY_FLAG) & 0xff)));
        Memory.instance.write(0xc000, (byte)0x90);
        Memory.instance.write(0xc001, (byte)0x10);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 2", 2, result);
        Assert.assertEquals("Program counter should equal 0xc012", 0xc012, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void op91() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setAccumulator((byte)0xaa);
        cpu.setyRegister((byte)0x01);
        Memory.instance.write(0xc000, (byte)0x91);
        Memory.instance.write(0xc001, (byte)0x32);
        Memory.instance.write(0x0032, (byte)0x02);
        Memory.instance.write(0x0033, (byte)0xc0);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 6", 6, result);
        Assert.assertEquals("Program counter should equal 0xc002", 0xc002, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
        Assert.assertEquals("Memory should equal 0xaa", 0xaa, (Memory.instance.read(0xc003) & 0xff));
    }
    @Test public void op92() throws Exception {}
    @Test public void op93() throws Exception {}
    @Test public void op94() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setyRegister((byte)0xaa);
        cpu.setxRegister((byte)0x01);
        Memory.instance.write(0xc000, (byte)0x94);
        Memory.instance.write(0xc001, (byte)0x31);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 4", 4, result);
        Assert.assertEquals("Program counter should equal 0xc002", 0xc002, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
        Assert.assertEquals("Memory should equal 0xaa", 0xaa, (Memory.instance.read(0x0032) & 0xff));
    }
    @Test public void op95() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setAccumulator((byte)0xaa);
        cpu.setxRegister((byte)0x01);
        Memory.instance.write(0xc000, (byte)0x95);
        Memory.instance.write(0xc001, (byte)0x31);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 4", 4, result);
        Assert.assertEquals("Program counter should equal 0xc002", 0xc002, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
        Assert.assertEquals("Memory should equal 0xaa", 0xaa, (Memory.instance.read(0x0032) & 0xff));
    }
    @Test public void op96() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setxRegister((byte)0xaa);
        cpu.setyRegister((byte)0x01);
        Memory.instance.write(0xc000, (byte)0x96);
        Memory.instance.write(0xc001, (byte)0x31);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 4", 4, result);
        Assert.assertEquals("Program counter should equal 0xc002", 0xc002, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
        Assert.assertEquals("Memory should equal 0xaa", 0xaa, (Memory.instance.read(0x0032) & 0xff));
    }
    @Test public void op97() throws Exception {}
    @Test public void op98() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setyRegister((byte)0xaa);
        Memory.instance.write(0xc000, (byte)0x98);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 2", 2, result);
        Assert.assertEquals("Program counter should equal 0xc001", 0xc001, cpu.getProgramCounter());
        Assert.assertEquals("Accumulator should equal 0xaa", 0xaa, (cpu.getAccumulator() & 0xff));
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void op99() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setAccumulator((byte)0xaa);
        cpu.setyRegister((byte)0x01);
        Memory.instance.write(0xc000, (byte)0x99);
        Memory.instance.write(0xc001, (byte)0x02);
        Memory.instance.write(0xc002, (byte)0xc0);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 5", 5, result);
        Assert.assertEquals("Program counter should equal 0xc003", 0xc003, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
        Assert.assertEquals("Memory should equal 0xaa", 0xaa, (Memory.instance.read(0xc003) & 0xff));
    }
    @Test public void op9a() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setxRegister((byte)0xaa);
        Memory.instance.write(0xc000, (byte)0x9a);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 2", 2, result);
        Assert.assertEquals("Program counter should equal 0xc001", 0xc001, cpu.getProgramCounter());
        Assert.assertEquals("Stack pointer should equal 0xaa", 0xaa, (cpu.getStackPointer() & 0xff));
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void op9b() throws Exception {}
    @Test public void op9c() throws Exception {}
    @Test public void op9d() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setAccumulator((byte)0xaa);
        cpu.setxRegister((byte)0x01);
        Memory.instance.write(0xc000, (byte)0x9d);
        Memory.instance.write(0xc001, (byte)0x02);
        Memory.instance.write(0xc002, (byte)0xc0);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 5", 5, result);
        Assert.assertEquals("Program counter should equal 0xc003", 0xc003, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
        Assert.assertEquals("Memory should equal 0xaa", 0xaa, (Memory.instance.read(0xc003) & 0xff));
    }
    @Test public void op9e() throws Exception {}
    @Test public void op9f() throws Exception {}

    @Test public void opa0() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setyRegister((byte)0xff);
        Memory.instance.write(0xc000, (byte)0xa0);
        Memory.instance.write(0xc001, (byte)0x88);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 2", 2, result);
        Assert.assertEquals("Program counter should equal 0xc002", 0xc002, cpu.getProgramCounter());
        Assert.assertEquals("Y Register should equal 0x88", 0x88, (cpu.getyRegister() & 0xff));
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void opa1() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setxRegister((byte)0x00);
        cpu.setAccumulator((byte)0xff);
        Memory.instance.write(0xc000, (byte)0xa1);
        Memory.instance.write(0xc001, (byte)0x32);
        Memory.instance.write(0x0032, (byte)0x03);
        Memory.instance.write(0x0033, (byte)0xc0);
        Memory.instance.write(0xc003, (byte)0x88);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 6", 6, result);
        Assert.assertEquals("Program counter should equal 0xc002", 0xc002, cpu.getProgramCounter());
        Assert.assertEquals("Accumulator should equal 0x88", 0x88, (cpu.getAccumulator() & 0xff));
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void opa2() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setxRegister((byte)0xff);
        Memory.instance.write(0xc000, (byte)0xa2);
        Memory.instance.write(0xc001, (byte)0x88);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 2", 2, result);
        Assert.assertEquals("Program counter should equal 0xc002", 0xc002, cpu.getProgramCounter());
        Assert.assertEquals("X Register should equal 0x88", 0x88, (cpu.getxRegister() & 0xff));
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void opa3() throws Exception {}
    @Test public void opa4() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setyRegister((byte)0xff);
        Memory.instance.write(0xc000, (byte)0xa4);
        Memory.instance.write(0xc001, (byte)0x32);
        Memory.instance.write(0x0032, (byte)0x88);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 3", 3, result);
        Assert.assertEquals("Program counter should equal 0xc002", 0xc002, cpu.getProgramCounter());
        Assert.assertEquals("Y Register should equal 0x88", 0x88, (cpu.getyRegister() & 0xff));
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void opa5() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setxRegister((byte)0x00);
        cpu.setAccumulator((byte)0xff);
        Memory.instance.write(0xc000, (byte)0xa5);
        Memory.instance.write(0xc001, (byte)0x32);
        Memory.instance.write(0x0032, (byte)0x88);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 3", 3, result);
        Assert.assertEquals("Program counter should equal 0xc002", 0xc002, cpu.getProgramCounter());
        Assert.assertEquals("Accumulator should equal 0x88", 0x88, (cpu.getAccumulator() & 0xff));
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void opa6() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setxRegister((byte)0xff);
        Memory.instance.write(0xc000, (byte)0xa6);
        Memory.instance.write(0xc001, (byte)0x32);
        Memory.instance.write(0x0032, (byte)0x88);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 3", 3, result);
        Assert.assertEquals("Program counter should equal 0xc002", 0xc002, cpu.getProgramCounter());
        Assert.assertEquals("X Register should equal 0x88", 0x88, (cpu.getxRegister() & 0xff));
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void opa7() throws Exception {}
    @Test public void opa8() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setAccumulator((byte)0xaa);
        Memory.instance.write(0xc000, (byte)0xa8);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 2", 2, result);
        Assert.assertEquals("Program counter should equal 0xc001", 0xc001, cpu.getProgramCounter());
        Assert.assertEquals("Y Register should equal 0xaa", 0xaa, (cpu.getyRegister() & 0xff));
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void opa9() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setxRegister((byte)0x00);
        cpu.setAccumulator((byte)0xff);
        Memory.instance.write(0xc000, (byte)0xa9);
        Memory.instance.write(0xc001, (byte)0x88);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 2", 2, result);
        Assert.assertEquals("Program counter should equal 0xc002", 0xc002, cpu.getProgramCounter());
        Assert.assertEquals("Accumulator should equal 0x88", 0x88, (cpu.getAccumulator() & 0xff));
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void opaa() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setAccumulator((byte)0xaa);
        Memory.instance.write(0xc000, (byte)0xaa);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 2", 2, result);
        Assert.assertEquals("Program counter should equal 0xc001", 0xc001, cpu.getProgramCounter());
        Assert.assertEquals("X Register should equal 0xaa", 0xaa, (cpu.getxRegister() & 0xff));
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void opab() throws Exception {}
    @Test public void opac() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setyRegister((byte)0xff);
        Memory.instance.write(0xc000, (byte)0xac);
        Memory.instance.write(0xc001, (byte)0x03);
        Memory.instance.write(0xc002, (byte)0xc0);
        Memory.instance.write(0xc003, (byte)0x88);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 4", 4, result);
        Assert.assertEquals("Program counter should equal 0xc003", 0xc003, cpu.getProgramCounter());
        Assert.assertEquals("Y Register should equal 0x88", 0x88, (cpu.getyRegister() & 0xff));
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void opad() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setxRegister((byte)0x00);
        cpu.setAccumulator((byte)0xff);
        Memory.instance.write(0xc000, (byte)0xad);
        Memory.instance.write(0xc001, (byte)0x03);
        Memory.instance.write(0xc002, (byte)0xc0);
        Memory.instance.write(0xc003, (byte)0x88);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 4", 4, result);
        Assert.assertEquals("Program counter should equal 0xc003", 0xc003, cpu.getProgramCounter());
        Assert.assertEquals("Accumulator should equal 0x88", 0x88, (cpu.getAccumulator() & 0xff));
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void opae() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setxRegister((byte)0xff);
        Memory.instance.write(0xc000, (byte)0xae);
        Memory.instance.write(0xc001, (byte)0x03);
        Memory.instance.write(0xc002, (byte)0xc0);
        Memory.instance.write(0xc003, (byte)0x88);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 4", 4, result);
        Assert.assertEquals("Program counter should equal 0xc003", 0xc003, cpu.getProgramCounter());
        Assert.assertEquals("X Register should equal 0x88", 0x88, (cpu.getxRegister() & 0xff));
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void opaf() throws Exception {}

    @Test public void opb0() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setProcessorStatus((byte)((cpu.getProcessorStatus() & 0xff) | Cpu.CARRY_FLAG));
        Memory.instance.write(0xc000, (byte)0xb0);
        Memory.instance.write(0xc001, (byte)0x10);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 2", 2, result);
        Assert.assertEquals("Program counter should equal 0xc012", 0xc012, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0xb5", 0xb5, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void opb1() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setyRegister((byte)0x00);
        cpu.setAccumulator((byte)0xff);
        Memory.instance.write(0xc000, (byte)0xb1);
        Memory.instance.write(0xc001, (byte)0x32);
        Memory.instance.write(0x0032, (byte)0x03);
        Memory.instance.write(0x0033, (byte)0xc0);
        Memory.instance.write(0xc003, (byte)0x88);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 5", 5, result);
        Assert.assertEquals("Program counter should equal 0xc002", 0xc002, cpu.getProgramCounter());
        Assert.assertEquals("Accumulator should equal 0x88", 0x88, (cpu.getAccumulator() & 0xff));
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void opb2() throws Exception {}
    @Test public void opb3() throws Exception {}
    @Test public void opb4() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setyRegister((byte)0xff);
        cpu.setxRegister((byte)0x01);
        Memory.instance.write(0xc000, (byte)0xb4);
        Memory.instance.write(0xc001, (byte)0x31);
        Memory.instance.write(0x0032, (byte)0x88);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 4", 4, result);
        Assert.assertEquals("Program counter should equal 0xc002", 0xc002, cpu.getProgramCounter());
        Assert.assertEquals("Y Register should equal 0x88", 0x88, (cpu.getyRegister() & 0xff));
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void opb5() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setxRegister((byte)0x00);
        cpu.setAccumulator((byte)0xff);
        Memory.instance.write(0xc000, (byte)0xb5);
        Memory.instance.write(0xc001, (byte)0x32);
        Memory.instance.write(0x0032, (byte)0x88);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 4", 4, result);
        Assert.assertEquals("Program counter should equal 0xc002", 0xc002, cpu.getProgramCounter());
        Assert.assertEquals("Accumulator should equal 0x88", 0x88, (cpu.getAccumulator() & 0xff));
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void opb6() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setxRegister((byte)0xff);
        cpu.setyRegister((byte)0x01);
        Memory.instance.write(0xc000, (byte)0xb6);
        Memory.instance.write(0xc001, (byte)0x31);
        Memory.instance.write(0x0032, (byte)0x88);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 4", 4, result);
        Assert.assertEquals("Program counter should equal 0xc002", 0xc002, cpu.getProgramCounter());
        Assert.assertEquals("X Register should equal 0x88", 0x88, (cpu.getxRegister() & 0xff));
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void opb7() throws Exception {}
    @Test public void opb8() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setProcessorStatus((byte)((cpu.getProcessorStatus() & 0xff) | Cpu.OVERFLOW_FLAG));
        Assert.assertEquals("Status register should equal 0xf4", 0xf4, (cpu.getProcessorStatus() & 0xff));
        Memory.instance.write(0xc000, (byte)0xb8);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 2", 2, result);
        Assert.assertEquals("Program counter should equal 0xc001", 0xc001, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void opb9() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setyRegister((byte)0x00);
        cpu.setAccumulator((byte)0xff);
        Memory.instance.write(0xc000, (byte)0xb9);
        Memory.instance.write(0xc001, (byte)0x03);
        Memory.instance.write(0xc002, (byte)0xc0);
        Memory.instance.write(0xc003, (byte)0x88);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 4", 4, result);
        Assert.assertEquals("Program counter should equal 0xc003", 0xc003, cpu.getProgramCounter());
        Assert.assertEquals("Accumulator should equal 0x88", 0x88, (cpu.getAccumulator() & 0xff));
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void opba() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setStackPointer((byte)0xaa);
        Memory.instance.write(0xc000, (byte)0xba);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 2", 2, result);
        Assert.assertEquals("Program counter should equal 0xc001", 0xc001, cpu.getProgramCounter());
        Assert.assertEquals("X Register should equal 0xaa", 0xaa, (cpu.getxRegister() & 0xff));
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void opbb() throws Exception {}
    @Test public void opbc() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setyRegister((byte)0xff);
        cpu.setxRegister((byte)0x01);
        Memory.instance.write(0xc000, (byte)0xbc);
        Memory.instance.write(0xc001, (byte)0x02);
        Memory.instance.write(0xc002, (byte)0xc0);
        Memory.instance.write(0xc003, (byte)0x88);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 4", 4, result);
        Assert.assertEquals("Program counter should equal 0xc003", 0xc003, cpu.getProgramCounter());
        Assert.assertEquals("Y Register should equal 0x88", 0x88, (cpu.getyRegister() & 0xff));
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void opbd() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setxRegister((byte)0x01);
        cpu.setAccumulator((byte)0xff);
        Memory.instance.write(0xc000, (byte)0xbd);
        Memory.instance.write(0xc001, (byte)0x02);
        Memory.instance.write(0xc002, (byte)0xc0);
        Memory.instance.write(0xc003, (byte)0x88);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 4", 4, result);
        Assert.assertEquals("Program counter should equal 0xc003", 0xc003, cpu.getProgramCounter());
        Assert.assertEquals("Accumulator should equal 0x88", 0x88, (cpu.getAccumulator() & 0xff));
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void opbe() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setxRegister((byte)0xff);
        cpu.setyRegister((byte)0x01);
        Memory.instance.write(0xc000, (byte)0xbe);
        Memory.instance.write(0xc001, (byte)0x02);
        Memory.instance.write(0xc002, (byte)0xc0);
        Memory.instance.write(0xc003, (byte)0x88);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 4", 4, result);
        Assert.assertEquals("Program counter should equal 0xc003", 0xc003, cpu.getProgramCounter());
        Assert.assertEquals("X Register should equal 0x88", 0x88, (cpu.getxRegister() & 0xff));
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void opbf() throws Exception {}

    @Test public void opc0() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setyRegister((byte)0xff);
        Memory.instance.write(0xc000, (byte)0xc0);
        Memory.instance.write(0xc001, (byte)0xff);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 2", 2, result);
        Assert.assertEquals("Program counter should equal 0xc002", 0xc002, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0x36", 0x36, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void opc1() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setAccumulator((byte)0xff);
        cpu.setxRegister((byte)0x00);
        Memory.instance.write(0xc000, (byte)0xc1);
        Memory.instance.write(0xc001, (byte)0x32);
        Memory.instance.write(0x0032, (byte)0x03);
        Memory.instance.write(0x0033, (byte)0xc0);
        Memory.instance.write(0xc003, (byte)0xff);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 6", 6, result);
        Assert.assertEquals("Program counter should equal 0xc002", 0xc002, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0x36", 0x36, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void opc2() throws Exception {
        testNop((byte)0xc2, 2);
    }
    @Test public void opc3() throws Exception {}
    @Test public void opc4() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setyRegister((byte)0xff);
        Memory.instance.write(0xc000, (byte)0xc4);
        Memory.instance.write(0xc001, (byte)0x32);
        Memory.instance.write(0x0032, (byte)0xff);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 3", 3, result);
        Assert.assertEquals("Program counter should equal 0xc002", 0xc002, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0x36", 0x36, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void opc5() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setAccumulator((byte)0xff);
        Memory.instance.write(0xc000, (byte)0xc5);
        Memory.instance.write(0xc001, (byte)0x32);
        Memory.instance.write(0x0032, (byte)0xff);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 3", 3, result);
        Assert.assertEquals("Program counter should equal 0xc002", 0xc002, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0x36", 0x36, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void opc6() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        Memory.instance.write(0xc000, (byte)0xc6);
        Memory.instance.write(0xc001, (byte)0x32);
        Memory.instance.write(0x0032, (byte)0xff);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 5", 5, result);
        Assert.assertEquals("Program counter should equal 0xc002", 0xc002, cpu.getProgramCounter());
        Assert.assertEquals("Memory should equal 0xfe", 0xfe, (Memory.instance.read(0x0032) & 0xff));
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void opc7() throws Exception {}
    @Test public void opc8() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setyRegister((byte)0x00);
        Memory.instance.write(0xc000, (byte)0xc8);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 2", 2, result);
        Assert.assertEquals("Program counter should equal 0xc001", 0xc001, cpu.getProgramCounter());
        Assert.assertEquals("Y Register should equal 0x01", 0x01, (cpu.getyRegister() & 0xff));
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void opc9() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setAccumulator((byte)0xff);
        Memory.instance.write(0xc000, (byte)0xc9);
        Memory.instance.write(0xc001, (byte)0xff);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 2", 2, result);
        Assert.assertEquals("Program counter should equal 0xc002", 0xc002, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0x36", 0x36, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void opca() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setxRegister((byte)0xff);
        Memory.instance.write(0xc000, (byte)0xca);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 2", 2, result);
        Assert.assertEquals("Program counter should equal 0xc001", 0xc001, cpu.getProgramCounter());
        Assert.assertEquals("X Register should equal 0xfe", 0xfe, (cpu.getxRegister() & 0xff));
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void opcb() throws Exception {}
    @Test public void opcc() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setyRegister((byte)0xff);
        Memory.instance.write(0xc000, (byte)0xcc);
        Memory.instance.write(0xc001, (byte)0x03);
        Memory.instance.write(0xc002, (byte)0xc0);
        Memory.instance.write(0xc003, (byte)0xff);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 4", 4, result);
        Assert.assertEquals("Program counter should equal 0xc003", 0xc003, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0x36", 0x36, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void opcd() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setAccumulator((byte)0xff);
        Memory.instance.write(0xc000, (byte)0xcd);
        Memory.instance.write(0xc001, (byte)0x03);
        Memory.instance.write(0xc002, (byte)0xc0);
        Memory.instance.write(0xc003, (byte)0xff);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 4", 4, result);
        Assert.assertEquals("Program counter should equal 0xc003", 0xc003, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0x36", 0x36, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void opce() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        Memory.instance.write(0xc000, (byte)0xce);
        Memory.instance.write(0xc001, (byte)0x03);
        Memory.instance.write(0xc002, (byte)0xc0);
        Memory.instance.write(0xc003, (byte)0xff);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 6", 6, result);
        Assert.assertEquals("Program counter should equal 0xc003", 0xc003, cpu.getProgramCounter());
        Assert.assertEquals("Memory should equal 0xfe", 0xfe, (Memory.instance.read(0xc003) & 0xff));
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void opcf() throws Exception {}

    @Test public void opd0() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setProcessorStatus((byte)((cpu.getProcessorStatus() & 0xff) & ((~Cpu.ZERO_FLAG) & 0xff)));
        Memory.instance.write(0xc000, (byte)0xd0);
        Memory.instance.write(0xc001, (byte)0x10);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 2", 2, result);
        Assert.assertEquals("Program counter should equal 0xc012", 0xc012, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void opd1() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setAccumulator((byte)0xff);
        cpu.setyRegister((byte)0x00);
        Memory.instance.write(0xc000, (byte)0xd1);
        Memory.instance.write(0xc001, (byte)0x32);
        Memory.instance.write(0x0032, (byte)0x03);
        Memory.instance.write(0x0033, (byte)0xc0);
        Memory.instance.write(0xc003, (byte)0xff);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 5", 5, result);
        Assert.assertEquals("Program counter should equal 0xc002", 0xc002, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0x36", 0x36, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void opd2() throws Exception {}
    @Test public void opd3() throws Exception {}
    @Test public void opd4() throws Exception {
        testNop((byte)0xd4, 4);
    }
    @Test public void opd5() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setAccumulator((byte)0xff);
        cpu.setxRegister((byte)0x00);
        Memory.instance.write(0xc000, (byte)0xd5);
        Memory.instance.write(0xc001, (byte)0x32);
        Memory.instance.write(0x0032, (byte)0xff);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 4", 4, result);
        Assert.assertEquals("Program counter should equal 0xc002", 0xc002, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0x36", 0x36, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void opd6() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setxRegister((byte)0x00);
        Memory.instance.write(0xc000, (byte)0xd6);
        Memory.instance.write(0xc001, (byte)0x32);
        Memory.instance.write(0x0032, (byte)0xff);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 6", 6, result);
        Assert.assertEquals("Program counter should equal 0xc002", 0xc002, cpu.getProgramCounter());
        Assert.assertEquals("Memory should equal 0xfe", 0xfe, (Memory.instance.read(0x0032) & 0xff));
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void opd7() throws Exception {}
    @Test public void opd8() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setProcessorStatus((byte)((cpu.getProcessorStatus() & 0xff) | Cpu.DECIMAL_FLAG));
        Assert.assertEquals("Status register should equal 0xbc", 0xbc, (cpu.getProcessorStatus() & 0xff));
        Memory.instance.write(0xc000, (byte)0xd8);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 2", 2, result);
        Assert.assertEquals("Program counter should equal 0xc001", 0xc001, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void opd9() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setAccumulator((byte)0xff);
        cpu.setyRegister((byte)0x00);
        Memory.instance.write(0xc000, (byte)0xd9);
        Memory.instance.write(0xc001, (byte)0x03);
        Memory.instance.write(0xc002, (byte)0xc0);
        Memory.instance.write(0xc003, (byte)0xff);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 4", 4, result);
        Assert.assertEquals("Program counter should equal 0xc003", 0xc003, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0x36", 0x36, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void opda() throws Exception {
        testNop((byte)0xda, 2);
    }
    @Test public void opdb() throws Exception {}
    @Test public void opdc() throws Exception {
        testNop((byte)0xdc, 4);
    }
    @Test public void opdd() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setAccumulator((byte)0xff);
        cpu.setxRegister((byte)0x00);
        Memory.instance.write(0xc000, (byte)0xdd);
        Memory.instance.write(0xc001, (byte)0x03);
        Memory.instance.write(0xc002, (byte)0xc0);
        Memory.instance.write(0xc003, (byte)0xff);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 4", 4, result);
        Assert.assertEquals("Program counter should equal 0xc003", 0xc003, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0x36", 0x36, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void opde() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setxRegister((byte)0x00);
        Memory.instance.write(0xc000, (byte)0xde);
        Memory.instance.write(0xc001, (byte)0x03);
        Memory.instance.write(0xc002, (byte)0xc0);
        Memory.instance.write(0xc003, (byte)0xff);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 7", 7, result);
        Assert.assertEquals("Program counter should equal 0xc003", 0xc003, cpu.getProgramCounter());
        Assert.assertEquals("Memory should equal 0xfe", 0xfe, (Memory.instance.read(0xc003) & 0xff));
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void opdf() throws Exception {}

    @Test public void ope0() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setxRegister((byte)0xff);
        Memory.instance.write(0xc000, (byte)0xe0);
        Memory.instance.write(0xc001, (byte)0xff);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 2", 2, result);
        Assert.assertEquals("Program counter should equal 0xc002", 0xc002, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0x36", 0x36, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void ope1() throws Exception {}
    @Test public void ope2() throws Exception {
        testNop((byte)0xe2, 2);
    }
    @Test public void ope3() throws Exception {}
    @Test public void ope4() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setxRegister((byte)0xff);
        Memory.instance.write(0xc000, (byte)0xe4);
        Memory.instance.write(0xc001, (byte)0x32);
        Memory.instance.write(0x0032, (byte)0xff);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 3", 3, result);
        Assert.assertEquals("Program counter should equal 0xc002", 0xc002, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0x36", 0x36, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void ope5() throws Exception {}
    @Test public void ope6() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        Memory.instance.write(0xc000, (byte)0xe6);
        Memory.instance.write(0xc001, (byte)0x32);
        Memory.instance.write(0x0032, (byte)0xff);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 5", 5, result);
        Assert.assertEquals("Program counter should equal 0xc002", 0xc002, cpu.getProgramCounter());
        Assert.assertEquals("memory should equal 0x00", 0x00, (Memory.instance.read(0x0032) & 0xff));
        Assert.assertEquals("Status register should equal 0x36", 0x36, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void ope7() throws Exception {}
    @Test public void ope8() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setxRegister((byte)0x00);
        Memory.instance.write(0xc000, (byte)0xe8);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 2", 2, result);
        Assert.assertEquals("Program counter should equal 0xc001", 0xc001, cpu.getProgramCounter());
        Assert.assertEquals("X Register should equal 0x01", 0x01, (cpu.getxRegister() & 0xff));
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void ope9() throws Exception {}
    @Test public void opea() throws Exception {
        testNop((byte)0xea, 2);
    }
    @Test public void opeb() throws Exception {}
    @Test public void opec() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setxRegister((byte)0xff);
        Memory.instance.write(0xc000, (byte)0xec);
        Memory.instance.write(0xc001, (byte)0x03);
        Memory.instance.write(0xc002, (byte)0xc0);
        Memory.instance.write(0xc003, (byte)0xff);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 4", 4, result);
        Assert.assertEquals("Program counter should equal 0xc003", 0xc003, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0x36", 0x36, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void oped() throws Exception {}
    @Test public void opee() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        Memory.instance.write(0xc000, (byte)0xee);
        Memory.instance.write(0xc001, (byte)0x03);
        Memory.instance.write(0xc002, (byte)0xc0);
        Memory.instance.write(0xc003, (byte)0xff);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 6", 6, result);
        Assert.assertEquals("Program counter should equal 0xc003", 0xc003, cpu.getProgramCounter());
        Assert.assertEquals("memory should equal 0x00", 0x00, (Memory.instance.read(0xc003) & 0xff));
        Assert.assertEquals("Status register should equal 0x36", 0x36, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void opef() throws Exception {}

    @Test public void opf0() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setProcessorStatus((byte)((cpu.getProcessorStatus() & 0xff) | Cpu.ZERO_FLAG));
        Memory.instance.write(0xc000, (byte)0xf0);
        Memory.instance.write(0xc001, (byte)0x10);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 2", 2, result);
        Assert.assertEquals("Program counter should equal 0xc012", 0xc012, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0xb6", 0xb6, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void opf1() throws Exception {}
    @Test public void opf2() throws Exception {}
    @Test public void opf3() throws Exception {}
    @Test public void opf4() throws Exception {
        testNop((byte)0xf4, 4);
    }
    @Test public void opf5() throws Exception {}
    @Test public void opf6() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setxRegister((byte)0x00);
        Memory.instance.write(0xc000, (byte)0xf6);
        Memory.instance.write(0xc001, (byte)0x32);
        Memory.instance.write(0x0032, (byte)0xff);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 6", 6, result);
        Assert.assertEquals("Program counter should equal 0xc002", 0xc002, cpu.getProgramCounter());
        Assert.assertEquals("memory should equal 0x00", 0x00, (Memory.instance.read(0x0032) & 0xff));
        Assert.assertEquals("Status register should equal 0x36", 0x36, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void opf7() throws Exception {}
    @Test public void opf8() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setProcessorStatus((byte)((cpu.getProcessorStatus() & 0xff) & ((~Cpu.DECIMAL_FLAG) & 0xff)));
        Memory.instance.write(0xc000, (byte)0xf8);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 2", 2, result);
        Assert.assertEquals("Program counter should equal 0xc001", 0xc001, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0xbc", 0xbc, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void opf9() throws Exception {}
    @Test public void opfa() throws Exception {
        testNop((byte)0xfa, 2);
    }
    @Test public void opfb() throws Exception {}
    @Test public void opfc() throws Exception {
        testNop((byte)0xfc, 4);
    }
    @Test public void opfd() throws Exception {}
    @Test public void opfe() throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        cpu.setxRegister((byte)0x00);
        Memory.instance.write(0xc000, (byte)0xfe);
        Memory.instance.write(0xc001, (byte)0x03);
        Memory.instance.write(0xc002, (byte)0xc0);
        Memory.instance.write(0xc003, (byte)0xff);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return 7", 7, result);
        Assert.assertEquals("Program counter should equal 0xc003", 0xc003, cpu.getProgramCounter());
        Assert.assertEquals("memory should equal 0x00", 0x00, (Memory.instance.read(0xc003) & 0xff));
        Assert.assertEquals("Status register should equal 0x36", 0x36, (cpu.getProcessorStatus() & 0xff));
    }
    @Test public void opff() throws Exception {}
    
    private void testNop(byte opcode, int cycles) throws Exception {
        Cpu cpu = new Cpu();
        cpu.setProgramCounter(0xc000);
        Memory.instance.write(0xc000, opcode);
        int result = cpu.callMethod("next");
        Assert.assertEquals("Should return " + cycles, cycles, result);
        Assert.assertEquals("Program counter should equal 0xc001", 0xc001, cpu.getProgramCounter());
        Assert.assertEquals("Status register should equal 0xb4", 0xb4, (cpu.getProcessorStatus() & 0xff));
    }
}
