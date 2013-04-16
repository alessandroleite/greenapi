/**
 * Copyright (c) 2012 GreenI2R
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package greenapi.gpi.measure;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.measure.quantity.Quantity;
import javax.measure.unit.BaseUnit;
import javax.measure.unit.SystemOfUnits;
import javax.measure.unit.Unit;

public interface Ratio extends Quantity
{
    /**
     * A ratio unit equals to <code>0.01</code> (standard name <code>%</code>).
     */
    Unit<Ratio> UNIT = RatioUnit.RATIO;

    public class RatioUnit extends SystemOfUnits
    {
        
        /**
         * Holds collection of NonSI units.
         */
        private static HashSet<Unit<?>> UNITS = new HashSet<Unit<?>>();
        
        /**
         * A ratio unit equals to <code>0.01</code> (standard name <code>%</code>).
         */
        private static final Unit<Ratio> RATIO = ratioUnit(new BaseUnit<Ratio>("%").divide(100));

        @Override
        public Set<Unit<?>> getUnits()
        {
            return Collections.unmodifiableSet(UNITS);
        }

        /**
         * Adds a new unit to the collection.
         * 
         * @param unit
         *            the unit being added.
         * @param <U>
         *            The unit's type.
         * @return <code>unit</code>.
         */
        private static <U extends Unit<?>> U ratioUnit(U unit)
        {
            UNITS.add(unit);
            return unit;
        }
    }
}
