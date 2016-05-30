package org.apache.maven.surefire.its.jiras;/* * Licensed to the Apache Software Foundation (ASF) under one * or more contributor license agreements.  See the NOTICE file * distributed with this work for additional information * regarding copyright ownership.  The ASF licenses this file * to you under the Apache License, Version 2.0 (the * "License"); you may not use this file except in compliance * with the License.  You may obtain a copy of the License at * *     http://www.apache.org/licenses/LICENSE-2.0 * * Unless required by applicable law or agreed to in writing, * software distributed under the License is distributed on an * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY * KIND, either express or implied.  See the License for the * specific language governing permissions and limitations * under the License. */import org.apache.maven.shared.utils.io.FileUtils;import org.apache.maven.surefire.its.fixture.SurefireJUnit4IntegrationTestCase;import org.apache.maven.surefire.its.fixture.SurefireLauncher;import org.junit.Before;import org.junit.Test;import java.io.File;import java.io.IOException;import static java.lang.System.getProperty;import static org.hamcrest.Matchers.greaterThanOrEqualTo;import static org.hamcrest.Matchers.is;import static org.junit.Assume.assumeThat;/** * @author <a href="mailto:tibordigana@apache.org">Tibor Digana (tibor17)</a> * @see {@linkplain https://jira.codehaus.org/browse/SUREFIRE-1211} * @since 2.19.1 */public class Surefire1211JUnitTestNgIT        extends SurefireJUnit4IntegrationTestCase{    @Before    public void before() throws IOException    {        File version = new File( "./target/Surefire1211JUnitTestNgIT.txt" );        version.createNewFile();        FileUtils.fileWrite( version, null, getProperty( "java.specification.version", "null" ) );        version = new File( "./target/Surefire1211JUnitTestNgIT_.txt" );        version.createNewFile();        FileUtils.fileWrite( version, null, getProperty( "java.version", "null" ) );        System.out.println( "java.specification.version: " + getProperty( "java.specification.version" ) );        System.err.println( "java.version: " + getProperty( "java.version" ) );    }    @Test    public void withJUnit()    {        System.out.println( "java.specification.version: " + getProperty( "java.specification.version" ) );        assumeThat( "java.specification.version: ",                          getProperty( "java.specification.version" ), is( greaterThanOrEqualTo( "1.7" ) ) );        unpack().threadCount( 1 )                .executeTest()                .verifyErrorFree( 2 );    }    @Test    public void withoutJUnit()    {        assumeThat( "java.specification.version: ",                          getProperty( "java.specification.version" ), is( greaterThanOrEqualTo( "1.7" ) ) );        unpack().threadCount( 1 )                .sysProp( "junit", "false" )                .executeTest()                .verifyErrorFree( 1 );    }    private SurefireLauncher unpack()    {        return unpack( "surefire-1211" );    }}