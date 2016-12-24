package getpid;

import jnr.ffi.*;
import jnr.ffi.Runtime;

/**
 * Gets the process ID of the current process, and that of its parent.
 */
public class Getpid {
    // Imagin that pid_t was a structure, not an int
    public static class Pid extends Struct {
        public Pid(Runtime runtime) {
            super(runtime);
        }
        public Signed32 value;
    }
    public interface LibC  {
        // Now there is a bug here because getpid return a pid_t, not a pid_t*
        Pid getpid();
        Pid getppid();
        // May be there should be an anotation for that:
        // @OutValue Pid getpid();
        // @OutValue Pid getppid();
    }

    public static void main(String[] args) {
        LibC libc = LibraryLoader.create(LibC.class).load("c");

        System.out.println("pid=" + libc.getpid().value + " parent pid=" + libc.getppid().value);
    }
}
