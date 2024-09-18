-- Script de inserção de labels no banco de dados com as chaves de tradução
INSERT INTO system_labels (label_name, created_at, update_at) VALUES
                            ('label.favoritos', NOW(), NOW()),
                            ('label.spam', NOW(), NOW()),
                            ('label.enviados', NOW(), NOW()),
                            ('label.rascunhos', NOW(), NOW()),
                            ('label.lixeira', NOW(), NOW()),
                            ('label.naoLido', NOW(), NOW());
