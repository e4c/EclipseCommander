package cane.brothers.e4.commander.handlers.internal;

import java.nio.file.Path;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cane.brothers.e4.commander.IdStorage;
import cane.brothers.e4.commander.event.PartEvents;

public class StoreNewPathPartHandler {

    private static final Logger log = LoggerFactory.getLogger(StoreNewPathPartHandler.class);

    @Inject
    @Named(IServiceConstants.ACTIVE_PART)
    private MPart activePart;

    @Inject
    private IEclipseContext context;

    @Inject
    @Optional
    @Execute
    public void execute(@UIEventTopic(PartEvents.TOPIC_PART_PATH_OPEN) final Path newPath) {

        // store root path
        context.set(IdStorage.STATE_ROOT_PATH, newPath);

        // Store the root path of opening part
        if (activePart != null && newPath != null) {
            Map<String, String> state = activePart.getPersistedState();
            state.put(IdStorage.STATE_ROOT_PATH, newPath.toString());

            if (log.isDebugEnabled()) {
                log.debug(newPath.toString() + " path was stored into active part state"); //$NON-NLS-1$
            }
        }
    }
}
