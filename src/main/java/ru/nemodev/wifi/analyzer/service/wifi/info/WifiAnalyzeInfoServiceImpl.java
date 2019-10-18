package ru.nemodev.wifi.analyzer.service.wifi.info;

import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.nemodev.wifi.analyzer.entity.wifi.info.WifiAnalyzeInfo;
import ru.nemodev.wifi.analyzer.repository.wifi.info.WifiAnalyzeInfoRepository;

import java.util.List;
import java.util.Optional;


public class WifiAnalyzeInfoServiceImpl implements WifiAnalyzeInfoService {

    private final WifiAnalyzeInfoRepository wifiAnalyzeInfoRepository;

    public WifiAnalyzeInfoServiceImpl(WifiAnalyzeInfoRepository wifiAnalyzeInfoRepository) {
        this.wifiAnalyzeInfoRepository = wifiAnalyzeInfoRepository;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Optional<WifiAnalyzeInfo> findById(String id) {
        return wifiAnalyzeInfoRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<WifiAnalyzeInfo> findBy(Pageable pageable) {
        return wifiAnalyzeInfoRepository.findAll(pageable).getContent();
    }
}
