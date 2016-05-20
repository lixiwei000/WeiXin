package cn.edu.ncut.model;

/**
 * @author NikoBelic
 * @create 16:26
 */
public class Course
{
    String year;
    String season;
    String courseNo;
    String courseName;
    String normalScore;
    String examScore;
    String finalScore;
    String normalPercent;
    String examType;
    String courseValue;
    String teacher;

    public Course()
    {}
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getCourseNo() {
        return courseNo;
    }

    public void setCourseNo(String courseNo) {
        this.courseNo = courseNo;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getNormalScore() {
        return normalScore;
    }

    public void setNormalScore(String normalScore) {
        this.normalScore = normalScore;
    }

    public String getExamScore() {
        return examScore;
    }

    public void setExamScore(String examScore) {
        this.examScore = examScore;
    }

    public String getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(String finalScore) {
        this.finalScore = finalScore;
    }

    public String getNormalPercent() {
        return normalPercent;
    }

    public void setNormalPercent(String normalPercent) {
        this.normalPercent = normalPercent;
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    public String getCourseValue() {
        return courseValue;
    }

    public void setCourseValue(String courseValue) {
        this.courseValue = courseValue;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "Course{" +
                "year='" + year + '\'' +
                ", season='" + season + '\'' +
                ", courseNo='" + courseNo + '\'' +
                ", courseName='" + courseName + '\'' +
                ", normalScore='" + normalScore + '\'' +
                ", examScore='" + examScore + '\'' +
                ", finalScore='" + finalScore + '\'' +
                ", noamalPercent='" + normalPercent + '\'' +
                ", examType='" + examType + '\'' +
                ", courseValue='" + courseValue + '\'' +
                ", teacher='" + teacher + '\'' +
                '}';
    }
}