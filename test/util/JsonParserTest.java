package util;

import com.maxkosh.webapp.model.Resume;
import com.maxkosh.webapp.model.Section;
import com.maxkosh.webapp.model.TextSection;
import com.maxkosh.webapp.util.JsonParser;
import org.junit.Assert;
import org.junit.Test;

import static com.maxkosh.webapp.storage.ResumeTestData.resume_1;

public class JsonParserTest {
    @Test
    public void testResume() throws Exception {
        String json = JsonParser.write(resume_1);
        System.out.println(json);
        Resume resume = JsonParser.read(json, Resume.class);
        Assert.assertEquals(resume_1, resume);
    }

    @Test
    public void write() throws Exception {
        Section section1 = new TextSection("Objective1");
        String json = JsonParser.write(section1, Section.class);
        System.out.println(json);
        Section section2 = JsonParser.read(json, Section.class);
        Assert.assertEquals(section1, section2);
    }
}
