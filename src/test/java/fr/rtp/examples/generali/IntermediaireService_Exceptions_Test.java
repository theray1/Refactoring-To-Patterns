package fr.rtp.examples.generali;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;


public class IntermediaireService_Exceptions_Test {

    private IntermediaireService service;
    @Mock
    private IParamsPortefeuilleService paramsPortefeuilleServiceMock;
    @Mock
    private IGenericService genericServiceMock;
    @Mock
    private IGestionIdentiteAgenceServiceProxy vitrineServiceMock;

    @Test
    //@Ignore
    public void should_throw_GecBusinessException_when_portefeuille_salarie_service_throw_businessException() throws Exception {
        String exceptionMessage = "qzguonqzopgn";
        BusinessException thrownException = new BusinessException(exceptionMessage);
        when(paramsPortefeuilleServiceMock.getParamsPortefeuilleSalarie(anyString()))
                .thenThrow(thrownException);

        GecBusinessException thrown = assertThrows(GecBusinessException.class, () -> {
            service.getGestionnaireEmailForDeclaration("anything", "anything");
        });
        assertSame(thrownException, thrown.getCause());
        assertEquals(exceptionMessage, thrown.getMessage());
        assertEquals(GecBusinessException.ERROR_SERVICE_BUSINESS, thrown.getKey());
    }

    @Test
    public void should_throw_GecBusinessException_when_portefeuille_trieste_service_throw_businessException() throws Exception {
        String exceptionMessage = "qzguonqzopgn";
        BusinessException thrownException = new BusinessException(exceptionMessage);
        when(paramsPortefeuilleServiceMock.getParamsPortefeuilleTrieste(anyString(), anyString()))
                .thenThrow(thrownException);
        try {
            service.getGestionnaireEmailForDeclaration("anything", "anything");
            fail("Should have thrown an exception at this point");
        } catch (GecBusinessException e) {
            assertSame(thrownException, e.getCause());
            assertEquals(exceptionMessage, e.getMessage());
            assertEquals(GecBusinessException.ERROR_SERVICE_BUSINESS, e.getKey());
        }
    }

    @Test
    public void should_throw_GecBusinessException_when_portefeuille_direct_service_throw_businessException() throws Exception {
        String exceptionMessage = "qzguonqzopgn";
        BusinessException thrownException = new BusinessException(exceptionMessage);
        when(paramsPortefeuilleServiceMock.getParamsPortefeuilleDirect(anyString(), anyString()))
                .thenThrow(thrownException);
        try {
            service.getGestionnaireEmailForDeclaration("anything", "anything");
            fail("Should have thrown an exception at this point");
        } catch (GecBusinessException e) {
            assertSame(thrownException, e.getCause());
            assertEquals(exceptionMessage, e.getMessage());
            assertEquals(GecBusinessException.ERROR_SERVICE_BUSINESS, e.getKey());
        }
    }

    @Test
    public void should_throw_GecTechnicalException_when_TechnicalException_occurs() throws Exception {
        String exceptionMessage = "qzguonqzopgn";
        TechnicalException thrownException = new TechnicalException(exceptionMessage);
        when(genericServiceMock.getInformationIntermediaire(anyString(), anyString()))
                .thenThrow(thrownException);
        try {
            service.getGestionnaireEmailForDeclaration("anything", "anything");
            fail("Should have thrown an exception at this point");
        } catch (GecTechnicalException e) {
            assertSame(thrownException, e.getCause());
            assertEquals(exceptionMessage, e.getMessage());
            assertEquals(GecTechnicalException.ERROR_SERVICE_TECHNICAL, e.getKey());
        }
    }

    @Test
    public void should_throw_GecTechnicalException_when_generic_service_throw_GecTechnicalException() throws Exception {
        GecTechnicalException thrownException = new GecTechnicalException(null, null, null);
        when(genericServiceMock.getInformationIntermediaire(anyString(), anyString()))
                .thenThrow(thrownException);
        try {
            service.getGestionnaireEmailForDeclaration("anything", "anything");
            fail("Should have thrown an exception at this point");
        } catch (GecTechnicalException e) {
            assertSame(thrownException, e);
        }
    }

    @Test
    @Disabled
    public void should_throw_GecBusinessException_when_vitrine_service_throw_businessException() throws Exception {
        String exceptionMessage = "qzguonqzopgn";
        IdentiteAgenceNonTrouveProxyBusinessException thrownException = new IdentiteAgenceNonTrouveProxyBusinessException(exceptionMessage);
        InformationIntermediaire dummyInfoIntermediaire = new InformationIntermediaire(new Bureau(), new Intermediaire());

        when(genericServiceMock.getInformationIntermediaire(anyString(), anyString())).thenReturn(dummyInfoIntermediaire);
        when(vitrineServiceMock.recupererInfosAgence(anyString()))
                .thenThrow(thrownException);
        try {
            service.getGestionnaireEmailForDeclaration("anything", "anything");
            fail("Should have thrown an exception at this point");
        } catch (GecBusinessException e) {
            assertSame(thrownException, e.getCause());
            assertEquals(exceptionMessage, e.getMessage());
            assertEquals(GecBusinessException.ERROR_SERVICE_BUSINESS, e.getKey());
        }
    }

    @BeforeEach
    public void initBeforeTest() throws Exception {
        initMocks(this);
        service = new IntermediaireService();
        ReflectionTestUtils.setField(service, "paramsPortefeuilleService", paramsPortefeuilleServiceMock, IParamsPortefeuilleService.class);
        ReflectionTestUtils.setField(service, "genericService", genericServiceMock, IGenericService.class);
        ReflectionTestUtils.setField(service, "vitrineService", vitrineServiceMock, IGestionIdentiteAgenceServiceProxy.class);
    }
}
