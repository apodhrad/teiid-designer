/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.extension;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.teiid.designer.extension.definition.ModelExtensionDefinitionParserTest;
import org.teiid.designer.extension.definition.ModelExtensionDefinitionTest;
import org.teiid.designer.extension.definition.ModelExtensionDefinitionValidatorTest;
import org.teiid.designer.extension.definition.ModelExtensionDefinitionWriterTest;
import org.teiid.designer.extension.properties.ModelExtensionPropertyDefinitionImplTest;
import org.teiid.designer.extension.properties.TranslationTest;
import org.teiid.designer.extension.registry.ModelExtensionRegistryTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ ModelExtensionDefinitionTest.class, ModelExtensionDefinitionParserTest.class,
        ModelExtensionRegistryTest.class, ModelExtensionDefinitionValidatorTest.class, ModelExtensionDefinitionWriterTest.class,
        TranslationTest.class, ModelExtensionPropertyDefinitionImplTest.class })
public class AllTests {
    // nothing to do
}