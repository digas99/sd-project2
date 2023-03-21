package utils;

import genclass.GenericIO;

/**
 *    Parametric FIFO derived from a parametric memory.
 *    Errors are reported.
 *
 *    @param <R> data type of stored objects
 */

public class MemFIFO<R> extends MemObject<R>
{
  /**
   *   Pointer to the first empty location.
   */

   private int inPnt;

  /**
   *   Pointer to the first occupied location.
   */

   private int outPnt;

  /**
   *   Signaling FIFO empty state.
   */

   private boolean empty;

  /**
   *   FIFO instantiation.
   *   The instantiation only takes place if the memory exists.
   *   Otherwise, an error is reported.
   *
   *     @param storage memory to be used
   *     @throws MemException when the memory does not exist
   */

  private int size;

   public MemFIFO (R [] storage) throws MemException
   {
     super (storage);
     inPnt = outPnt = size = 0;
     empty = true;
   }

  /**
   *   FIFO insertion.
   *   A parametric object is written into it.
   *   If the FIFO is full, an error is reported.
   *
   *    @param val parametric object to be written
   *    @throws MemException when the FIFO is full
   */

   @Override
   public void write (R val) throws MemException
   {
     if ((inPnt != outPnt) || empty)
        { mem[inPnt] = val;
          inPnt = (inPnt + 1) % mem.length;
          empty = false;
          size++;
        }
        else throw new MemException ("Fifo full!");
   }

  /**
   *   FIFO retrieval.
   *   A parametric object is read from it.
   *   If the FIFO is empty, an error is reported.
   *
   *    @return first parametric object that was written
   *    @throws MemException when the FIFO is empty
   */

   @Override
   public R read () throws MemException
   {
     R val;

     if (!empty)
        { val = mem[outPnt];
          outPnt = (outPnt + 1) % mem.length;
          empty = (inPnt == outPnt);
          size--;
        }
        else throw new MemException ("Fifo empty!");
     return val;
   }

  /**
   *   Test FIFO current full status.
   *
   *    @return true, if FIFO is full -
   *            false, otherwise
   */

   public boolean isFull ()
   {
     return !((inPnt != outPnt) || empty);
   }

   public int size() {
       return size;
   }

   public void print () {
       for (int i = 0; i < size; i++)
           GenericIO.writelnString(mem[i].toString());
   }

   public void println () {
       for (int i = 0; i < size; i++)
           GenericIO.writelnString(mem[i].toString());
   }

   public boolean has (R value) {
       for (int i = 0; i < size; i++) {
           if (mem[i].equals(value))
               return true;
       }
       return false;
   }
}
