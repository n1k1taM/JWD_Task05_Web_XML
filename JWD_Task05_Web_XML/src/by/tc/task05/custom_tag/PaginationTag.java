package by.tc.task05.custom_tag;


import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class PaginationTag extends SimpleTagSupport {

    int currentPage;
    int maxPage;
    String parsType;

    public void setParsType(String parsType) {
        this.parsType = parsType;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }

    @Override
    public void doTag() throws JspException, IOException {

        JspWriter out = getJspContext().getOut();
        out.print("<table>");
        out.print("<tr>");

        out.print(createForm(1));

        if(currentPage <= 2){
            out.print(createForm(2));
            out.print(createForm(3));
        }

        if(currentPage > 3){
            out.print("<td>...</td>");
        }

        if(currentPage > 2 && currentPage < maxPage - 1){
            for(int i = currentPage - 1; i <= currentPage + 1; i++){
                out.print(createForm(i));
            }
        }

        if(currentPage >= maxPage - 1){
            out.print(createForm(maxPage - 2));
            out.print(createForm(maxPage - 1));
        }

        if(currentPage < maxPage - 2){
            out.print("<td>...</td>");
        }

        out.print(createForm(maxPage));

        out.print("</tr>");
        out.print("</table>");

    }

    private String createForm(int page){

        StringBuilder form = new StringBuilder();

        form.append("<td>").append("<form action=\"Controller\" method=\"get\">")
                .append("<input type=\"submit\" name=\"pageNumber\" value = \"").append(page).append("\" >")
                .append("<input type=\"hidden\" name=\"parserType\" value = \"").append(parsType).append("\" >")
                .append("</form>").append("</td>");

        return form.toString();
    }
}