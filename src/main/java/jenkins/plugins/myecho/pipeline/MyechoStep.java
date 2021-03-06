package jenkins.plugins.myecho.pipeline;

import javax.inject.Inject;

import org.jenkinsci.plugins.workflow.steps.AbstractStepDescriptorImpl;
import org.jenkinsci.plugins.workflow.steps.AbstractStepImpl;
import org.jenkinsci.plugins.workflow.steps.AbstractSynchronousStepExecution;
import org.jenkinsci.plugins.workflow.steps.StepContextParameter;
import org.kohsuke.stapler.DataBoundConstructor;

import hudson.Extension;
import hudson.model.TaskListener;

public class MyechoStep extends AbstractStepImpl {

    private final String message;

    @DataBoundConstructor
    public MyechoStep(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Extension
    public static class DescriptorImpl extends AbstractStepDescriptorImpl {

        public DescriptorImpl() {
            super(Execution.class);
        }

        @Override
        public String getFunctionName() {
            return "myecho";
        }

        @Override
        public String getDisplayName() {
            return "My Echo";
        }
    }

    public static class Execution extends AbstractSynchronousStepExecution<Void> {

        @Inject
        private transient MyechoStep step;

        @StepContextParameter
        private transient TaskListener listener;

        @Override
        protected Void run() throws Exception {
            listener.getLogger().println(step.getMessage());
            return null;
        }

        private static final long serialVersionUID = 1L;

    }

}
