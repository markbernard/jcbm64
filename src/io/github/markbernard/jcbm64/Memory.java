/**
 * 
 */
package io.github.markbernard.jcbm64;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mark Bernard
 *
 */
public class Memory {
    private final List<Range> romRanges = new ArrayList<Range>();
    private final byte ram[] = new byte[65536];
    private final byte rom[] = new byte[65536];
    
    public static Memory instance = new Memory();
    
    private Memory() {
        try {
            loadRoms();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public final static void reset() {
        instance = new Memory();
    }
    
    public final void write(int address, byte value) {
        ram[address] = value;
    }
    
    public final byte read(int address) {
        for(Range range:romRanges) {
            if(range.inRange(address)) {
                return rom[address];
            }
        }
        
        return ram[address];
    }
    
    private void loadRoms() throws Exception {
        getClass().getResourceAsStream("/INITIAL.RAM").read(ram);
        getClass().getResourceAsStream("/BASIC.ROM").read(rom, 0xa000, 8192);
        romRanges.add(new Range(0xa000, 0xbfff));
        getClass().getResourceAsStream("/KERNAL.ROM").read(rom, 0xe000, 8192);
        romRanges.add(new Range(0xe000, 0xffff));
    }
    
    public static void main(String... args) throws Exception {
        Memory m = new Memory();
        m.write(0xa000, (byte)20);
        System.out.println(m.read(0xa000));
        m.write(0x9fff, (byte)20);
        System.out.println(m.read(0x9fff));
    }
}
