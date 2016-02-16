package jp.co.nichiwa_system.wicket_todo;

import java.sql.Date;
import java.sql.Timestamp;
import static org.hamcrest.Matchers.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import jp.co.nichiwa_system.wicket_todo.infrastracture.WicketApplication;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Simple test using the WicketTester
 */
public class TestHomePage {

    private WicketTester tester;

    @Before
    public void setUp() {
        tester = new WicketTester(new WicketApplication());
    }

    @Test
    public void ホームページが表示されること() {
        //start and render the test page
        tester.startPage(HomePage.class);

        //assert rendered page class
        tester.assertRenderedPage(HomePage.class);
    }

    @Test
    public void 初期状態ではTodoリストが空であること() {
        tester.startPage(HomePage.class);
        tester.assertListView("todoList:list", Collections.emptyList());
    }

    @Test
    public void タイトルと期日を入力して追加ボタンを押すと入力したとおりのタスクが追加されること() throws ParseException {
        tester.startPage(HomePage.class);
        FormTester form = tester.newFormTester("todoList");

        form.setValue("nextTask.description", "新しいタスクの説明");
        form.setValue("nextTask.due", "2016/2/1");
        form.submit("add");
        
        ListView<Todo> listView = (ListView<Todo>)tester.getComponentFromLastRenderedPage("todoList:list");
        Assert.assertThat(listView.getList(), hasItem(
                both(hasProperty("description", is("新しいタスクの説明")))
                .and(hasProperty("due", is(Timestamp.valueOf("2016-02-01 00:00:00"))))));
    }
}
