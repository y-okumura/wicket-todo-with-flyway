/*
 * Copyright 2016 y-okumura.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jp.co.nichiwa_system.wicket_todo.infrastracture;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import org.flywaydb.core.Flyway;

/**
 *
 * @author y-okumura
 */
@WebListener
public class DBMigrator implements ServletContextListener {

    @Resource(name = "jdbc/Todo")
    private DataSource ds;
    
    @Override
    public void contextInitialized(ServletContextEvent event) {
        Flyway flyway = new Flyway();
        flyway.setDataSource(ds);
        flyway.setOutOfOrder(true);
        flyway.repair();
        
        flyway.migrate();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) { }
    
}
