package cane.brothers.e4.commander.handlers.internal;

import java.nio.file.Path;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cane.brothers.e4.commander.event.PartEvents;
import cane.brothers.e4.commander.service.api.IPartService;
import cane.brothers.e4.commander.service.api.ITabService;

public class RefreshPathPartHandler {

    private static final Logger log = LoggerFactory.getLogger(RefreshPathPartHandler.class);

    @Inject
    @Named(IServiceConstants.ACTIVE_PART)
    private MPart activePart;

    @Inject
    ITabService tabService;

    @Inject
    private IPartService partService;

    @Inject
    @Optional
    @Execute
    public void execute(@UIEventTopic(PartEvents.TOPIC_PART_PATH_REFRESH) final Path newPath) {
        if (log.isDebugEnabled()) {
            log.debug(this.getClass().getSimpleName() + " called"); //$NON-NLS-1$
        }

        if (tabService != null && partService != null && activePart != null) {

            // resolve selection
            tabService.clearSelection(partService.getInactivePart(activePart));
            tabService.setSelection(activePart);
        }
    }

}
