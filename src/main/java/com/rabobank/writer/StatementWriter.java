package com.rabobank.writer;

import javax.servlet.http.HttpServletResponse;

public interface StatementWriter {

	public void writeOutputReport(HttpServletResponse response);

}
