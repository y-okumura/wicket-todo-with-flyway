package jp.co.nichiwa_system.wicket_todo;

import javax.inject.Inject;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.datetime.markup.html.basic.DateLabel;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.CompoundPropertyModel;

public class HomePage extends WebPage {

    private static final long serialVersionUID = 1L;
    
    @Inject
    private TodoRepositry todoRepositry;

    @SuppressWarnings("LeakingThisInConstructor")
    public HomePage(final PageParameters parameters) {
        super(parameters);
        
        Injector.get().inject(this);

        add(
            new Form<TodoList>("todoList", new CompoundPropertyModel<>(new TodoList(todoRepositry))) {
                @Override
                protected void onSubmit() {
                    getModelObject().addTask();
                }
            }.add(
                new PropertyListView<Todo>("list") {
                    @Override
                    protected void populateItem(ListItem<Todo> li) {
                        li.add(
                            new CheckBox("done"),
                            new Label("description"),
                            DateLabel.forShortStyle("due")
                        );
                    }
                },
                new TextField<String>("nextTask.description").setRequired(true),
                DateTextField.forShortStyle("nextTask.due").add(new DatePicker().setShowOnFieldClick(true).setAutoHide(true)),
                new AjaxButton("add") {
                    @Override
                    protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                        target.add(form);
                    }
                }
            ).setOutputMarkupId(true)
        );
    }
}
