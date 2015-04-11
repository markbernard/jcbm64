/**
 * 
 */
package io.github.markbernard.jcbm64;

/**
 * @author Mark Bernard
 *
 */
public class Range {
    private final int low;
    private final int high;
    private boolean enabled;
    
    public Range(int low, int high) {
        this.low = low;
        this.high = high;
        enabled = true;
    }
    
    public final boolean inRange(int number) {
        return (enabled && (low <= number && number <= high));
    }

    /**
     * @return the enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * @param enabled the enabled to set
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

}
