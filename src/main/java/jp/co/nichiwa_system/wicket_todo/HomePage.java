package jp.co.nichiwa_system.wicket_todo;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.datetime.markup.html.basic.DateLabel;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

	public HomePage(final PageParameters parameters) {
		super(parameters);
		
		add(
			new Form<TodoList>("todoList", new CompoundPropertyModel<>(new TodoList())) {
				@Override
				protected void onSubmit() {
					getModelObject().newTask();
				}
			}.add(
				new ListView<Todo>("list") {
					@Override
					protected void populateItem(ListItem<Todo> li) {
						Todo todo = li.getModelObject();
						li.add(
							new CheckBox("done", new PropertyModel(todo, "done")),
							new Label("description", new PropertyModel(todo, "description")),
							DateLabel.forShortStyle("due", new PropertyModel(todo, "due"))
						);
					}
				},
				new TextField<String>("description").setRequired(true),
				DateTextField.forShortStyle("due").add(new DatePicker().setShowOnFieldClick(true).setAutoHide(true)),
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
