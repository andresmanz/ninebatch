package org.cethos.tools.ninebatch.tests.tasks;

import org.cethos.tools.ninebatch.tasks.BatchConversionTask;
import org.cethos.tools.ninebatch.tasks.ConversionFailureException;
import org.cethos.tools.ninebatch.tasks.ImageQueryTask;
import org.cethos.tools.ninebatch.tasks.Task;
import org.cethos.tools.ninebatch.tasks.TaskFactory;
import org.cethos.tools.ninebatch.tests.testutil.CommandLineUtil;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertTrue;

public class TaskFactoryTest
{
    private TaskFactory taskFactory;

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp()
    {
        this.taskFactory = new TaskFactory();
    }

    @Test
    public void testCreateFrom_withoutArgs() throws Exception
    {
        thrown.expect(ConversionFailureException.class);
        taskFactory.createFrom(new String[0]);
    }

    @Test
    public void testCreateFrom_withInvalidArgs() throws Exception
    {
        thrown.expect(ConversionFailureException.class);
        taskFactory.createFrom(CommandLineUtil.getArgsFrom("--doesnotexist"));
    }

    @Test
    public void testCreateFrom_withArgsForBatchProcessor() throws Exception
    {
        final String[] args = CommandLineUtil.getArgsFrom("testdirectory");
        final Task processor = taskFactory.createFrom(args);
        assertTrue(processor instanceof BatchConversionTask);
    }

    @Test
    public void testCreateFrom_imageQueryArgs() throws Exception
    {
        final String[] args = CommandLineUtil.getArgsFrom("-q testdirectory");
        final Task processor = taskFactory.createFrom(args);
        assertTrue(processor instanceof ImageQueryTask);
    }

    @Test
    public void testCreateFrom_imageQueryAndBatchProcessorArgs() throws Exception
    {
        final String[] args = CommandLineUtil.getArgsFrom("-q -o test testdirectory");
        final Task processor = taskFactory.createFrom(args);
        assertTrue(processor instanceof ImageQueryTask);
    }
}
