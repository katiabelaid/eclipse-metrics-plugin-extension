/*
 * Copyright (c) 2003 Frank Sauer. All rights reserved.
 *
 * Licenced under CPL 1.0 (Common Public License Version 1.0).
 * The licence is available at http://www.eclipse.org/legal/cpl-v10.html.
 *
 *
 * DISCLAIMER OF WARRANTIES AND LIABILITY:
 *
 * THE SOFTWARE IS PROVIDED "AS IS".  THE AUTHOR MAKES  NO REPRESENTATIONS OR WARRANTIES,
 * EITHER EXPRESS OR IMPLIED.  TO THE EXTENT NOT PROHIBITED BY LAW, IN NO EVENT WILL THE
 * AUTHOR  BE LIABLE FOR ANY DAMAGES, INCLUDING WITHOUT LIMITATION, LOST REVENUE,  PROFITS
 * OR DATA, OR FOR SPECIAL, INDIRECT, CONSEQUENTIAL, INCIDENTAL  OR PUNITIVE DAMAGES,
 * HOWEVER CAUSED AND REGARDLESS OF THE THEORY OF  LIABILITY, ARISING OUT OF OR RELATED TO
 * ANY FURNISHING, PRACTICING, MODIFYING OR ANY USE OF THE SOFTWARE, EVEN IF THE AUTHOR
 * HAVE BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 *
 *
 * $id$
 */
package net.sourceforge.metrics.calculators.qmood;

import java.util.Map;

import net.sourceforge.metrics.calculators.Calculator;
import net.sourceforge.metrics.calculators.InvalidSourceException;
import net.sourceforge.metrics.core.Avg;
import net.sourceforge.metrics.core.Constants;
import net.sourceforge.metrics.core.Metric;
import net.sourceforge.metrics.core.sources.AbstractMetricSource;

/**
 * Calculator for the Extendibility metric - QMOOD
 * 
 * @author Leonardo Jr.
 */
public class Extendibility extends Calculator implements Constants {

	/**
	 * Constructor for EXT.
	 */
	public Extendibility() {
		super(EXT);
	}

	/**
	 * @see net.sourceforge.metrics.calculators.Calculator#calculate(net.sourceforge.metrics.core.sources.AbstractMetricSource)
	 */
	@Override
	public void calculate(AbstractMetricSource source) throws InvalidSourceException {
		if (source.getLevel() != PROJECT) {
			throw new InvalidSourceException("Extendibility is only applicable to projects");
		}
		try {
			Map<String,Avg> averages = source.getAverages();
			double valor = 0.5*source.getValue(Metric.ANA).getValue() 
						 - 0.5*averages.get("typeDCC").getValue() 
						 + 0.5*averages.get("typeMFA").getValue() 
						 + 0.5*averages.get("typeNOPM").getValue(); 
			source.setValue(new Metric(EXT,valor));
		} catch (Exception e) {
			source.setValue(new Metric(EXT, 0));
		}
	}
}
