package com.rabobank.writer;

import javax.servlet.http.HttpServletResponse;

/**
 * @author vinesh
 *
 */
public interface StatementWriter {

	public void writeOutputReport(HttpServletResponse response);

}
