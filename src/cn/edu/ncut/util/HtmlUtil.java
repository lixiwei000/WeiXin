package cn.edu.ncut.util;

import cn.edu.ncut.model.Course;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * @author NikoBelic
 * @create 15:44
 */
public class HtmlUtil
{
    public static List<Course> parseCourse(String url,String cookie)
    {
        String html = HttpUtil.getInfoLoadInstance().loadForString(url,cookie);
        //System.out.println(html);
        //System.out.println("====================================");
        Document doc = Jsoup.parse(html);

        Elements table = doc.select("table[id='Table7']");
        Elements trs = table.select("tr");
        boolean isFirst = true;
        List<Course> courses = new ArrayList<Course>();
        for (Element tr : trs)
        {
            int i = 0;
            if (isFirst)
            {
                isFirst = false;
                System.out.println(tr);
                continue;
            }

            Course course = new Course();
            course.setYear(tr.select("td").get(i++).text());
            course.setSeason(tr.select("td").get(i++).text());
            course.setCourseNo(tr.select("td").get(i++).text());
            course.setCourseName(tr.select("td").get(i++).text());
            course.setNormalScore(tr.select("td").get(i++).text());
            course.setExamScore(tr.select("td").get(i++).text());
            course.setFinalScore(tr.select("td").get(i++).text());
            course.setNormalPercent(tr.select("td").get(i++).text());
            course.setExamType(tr.select("td").get(i++).text());
            course.setCourseValue(tr.select("td").get(i++).text());
            course.setTeacher(tr.select("td").get(i++).text());
            courses.add(course);
        }

        return courses;
    }
    //public static void main(String[] args)
    //{
    //    parseCourse("http://jxxx.ncut.edu.cn/xs/cjkb.asp?id=5&jb=A&rxnf=2015");
    //}

}
